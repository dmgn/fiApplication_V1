package com.floatinvoice.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import com.floatinvoice.common.LoanStatus;
import com.floatinvoice.messages.FraudInvoiceDtls;
import com.floatinvoice.messages.LoanAgreementTemplateDtls;

public class JdbcLoanAgreementDao implements LoanAgreementDao{

	private NamedParameterJdbcTemplate jdbcTemplate;
	private LobHandler lobHandler;
	
	public JdbcLoanAgreementDao(){
		
	}
	
	public JdbcLoanAgreementDao( DataSource dataSource, LobHandler lobHandler){
		 jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
		 this.lobHandler = lobHandler;
		
	}

	@Override
	public List<LoanAgreementTemplateDtls> fetchPendingAgreementsForApprovedLoans() {
		final String sql = "SELECT ORG_ADDR.STREET, ORG_ADDR.CITY, ORG_ADDR.STATE, ORG_ADDR.ZIP_CODE, ORG_ADDR.COUNTRY, "
				+ " SUI.DIRECTOR_FNAME, SUI.DIRECTOR_LNAME, LI.LOAN_ID, LI.LOAN_REF_ID, LI.LOAN_AMT, LI.LOAN_DISPATCH_DT, "
				+ " LI.LOAN_CLOSE_DT, LI.SME_ORG_ID, LI.FINANCIER_ORG_ID, LAD.AGREEMENT, LAD.REF_ID AS AGREEMENT_REF_ID,"
				+ " LENDER_COMPANY.COMPANY_NAME AS LENDER_COMPANY_NAME, "
				+ " BORROWER_COMPANY.COMPANY_NAME AS BORROWER_COMPANY_NAME "
				+ " FROM LOAN_INFO LI "
				+ " JOIN LENDER_AGREEMENT_DOC LAD "
				+ " ON LI.FINANCIER_ORG_ID = LAD.COMPANY_ID "
				+ " JOIN ORGANIZATION_INFO LENDER_COMPANY "
				+ " ON LENDER_COMPANY.COMPANY_ID = LAD.COMPANY_ID "
				+ " JOIN ORGANIZATION_INFO BORROWER_COMPANY "
				+ " ON BORROWER_COMPANY.COMPANY_ID = LI.SME_ORG_ID "
				+ " JOIN USER_ORGANIZATION_MAP UOM "
				+ " ON UOM.COMPANY_ID = BORROWER_COMPANY.COMPANY_ID "
				+ " JOIN SME_USER_INFO SUI "
				+ " ON SUI.USER_ID = UOM.USER_ID "
				+ " JOIN ORGANIZATION_ADDRESS ORG_ADDR "
				+ " ON ORG_ADDR.COMPANY_ID = LENDER_COMPANY.COMPANY_ID "
				+ " WHERE LI.LOAN_STATUS = :status AND LI.LOAN_AGREEMENT IS NULL" ;
		
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("status", LoanStatus.SANCTIONED.getCode());
		//List<LoanAgreementTemplateDtls> lst = jdbcTemplate.query(sql, paramMap, new LoanAgreementTemplateRowMapper());
		
		List<LoanAgreementTemplateDtls> lst = jdbcTemplate.query(sql, paramMap, new ResultSetExtractor<List<LoanAgreementTemplateDtls>>() {

			@Override
			public List<LoanAgreementTemplateDtls> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				List<LoanAgreementTemplateDtls> resultList = new LinkedList<>();
				Map<String, LoanAgreementTemplateDtls> resultMap = new LinkedHashMap<>();
				String loanRefId = null;
				while(rs.next()){
					loanRefId = rs.getString("LOAN_REF_ID");
					LoanAgreementTemplateDtls row = null;
					if(!resultMap.containsKey(loanRefId)){
						row = new LoanAgreementTemplateDtls();
						resultList.add(row);
						resultMap.put(loanRefId, row);						
						List<String> companyDirectors = new LinkedList<>();
						row.getCompanyDirectors().addAll(companyDirectors);
						row.setLoanId(rs.getInt("LOAN_ID"));
						row.setRefId(rs.getString("LOAN_REF_ID"));
						row.setLoanAmount(rs.getString("LOAN_AMT"));
						row.setLoanDispatchDt(rs.getDate("LOAN_DISPATCH_DT"));
						row.setLoanCloseDt(rs.getDate("LOAN_CLOSE_DT"));
						row.setSmeOrgId(rs.getInt("SME_ORG_ID"));
						row.setLenderOrgId(rs.getInt("FINANCIER_ORG_ID"));
						row.setLoanAgreementTemplate(rs.getBinaryStream("AGREEMENT"));
						row.setAgreementRefId(rs.getString("AGREEMENT_REF_ID"));
						row.setLenderCompanyName(rs.getString("LENDER_COMPANY_NAME"));
						row.setBorrowerCompanyName(rs.getString("BORROWER_COMPANY_NAME"));
						row.setStreet(rs.getString("STREET"));
						row.setCity(rs.getString("CITY"));						
						row.setPinCode(rs.getString("ZIP_CODE"));
						row.setState(rs.getString("STATE"));
						row.setCountry(rs.getString("COUNTRY"));
					}else{
						row = resultMap.get(loanRefId);
					}
					row.getCompanyDirectors().add(rs.getString("DIRECTOR_FNAME") + " " + rs.getString("DIRECTOR_LNAME"));
				}
				
				return resultList;
			}
		});
		
		return lst;
	}

	private class LoanAgreementTemplateRowMapper implements RowMapper<LoanAgreementTemplateDtls>{

		@Override
		public LoanAgreementTemplateDtls mapRow(ResultSet rs, int arg1)
				throws SQLException {
			LoanAgreementTemplateDtls row = new LoanAgreementTemplateDtls();
			row.setLoanId(rs.getInt("LOAN_ID"));
			row.setRefId(rs.getString("LOAN_REF_ID"));
			row.setLoanAmount(rs.getString("LOAN_AMT"));
			row.setLoanDispatchDt(rs.getDate("LOAN_DISPATCH_DT"));
			row.setLoanCloseDt(rs.getDate("LOAN_CLOSE_DT"));
			row.setSmeOrgId(rs.getInt("SME_ORG_ID"));
			row.setLenderOrgId(rs.getInt("FINANCIER_ORG_ID"));
			row.setLoanAgreementTemplate(rs.getBinaryStream("AGREEMENT"));
			row.setAgreementRefId(rs.getString("AGREEMENT_REF_ID"));
			row.setLenderCompanyName(rs.getString("LENDER_COMPANY_NAME"));
			row.setBorrowerCompanyName(rs.getString("BORROWER_COMPANY_NAME"));
			return row;
		}
		
	}

	@Override
	public void updateAgreement(final byte[] bytes, final int loanId) {

		final String sql = "UPDATE LOAN_INFO LI SET LI.LOAN_AGREEMENT = ? WHERE LI.LOAN_ID = ?";
				
		final LobCreator lobCreator = lobHandler.getLobCreator();
		jdbcTemplate.getJdbcOperations().update( new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {

				final PreparedStatement ps = conn.prepareStatement(sql);
				try {
				
					lobCreator.setBlobAsBytes(ps, 1, bytes);
					ps.setInt(2, loanId);
				} catch (SQLException e) {
					throw e;
				}
				return ps;
			}
		});
		lobCreator.close();
		
		
	}

	@Override
	public List<String> fetchTemplateParams(String refId) {
		final String sql = " SELECT PARAM_NAME FROM LENDER_AGREEMENT_DOC_PARAMS LADA "
				+ " JOIN LENDER_AGREEMENT_DOC LAD "
				+ " ON LAD.ID = LADA.ID "
				+ " WHERE LAD.REF_ID = :refId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("refId", refId);
		List<Map<String, Object>> queryResultSet = jdbcTemplate.query(sql, paramMap, new ColumnMapRowMapper());
		List<String> result = new ArrayList<>();
		for ( Map<String, Object> map : queryResultSet ){
			result.add((String) map.get("PARAM_NAME"));
		}
		return result;
	}
}
