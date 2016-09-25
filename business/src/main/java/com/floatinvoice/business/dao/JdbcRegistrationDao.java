package com.floatinvoice.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.transaction.annotation.Transactional;

import com.floatinvoice.common.RegistrationStatusEnum;
import com.floatinvoice.common.UUIDGenerator;
import com.floatinvoice.common.UserContext;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.RegistrationStep1SignInDtlsMsg;
import com.floatinvoice.messages.RegistrationStep2CorpDtlsMsg;
import com.floatinvoice.messages.RegistrationStep3UserPersonalDtlsMsg;
import com.floatinvoice.messages.SupportDocDtls;
import com.floatinvoice.messages.UploadMessage;

@Transactional
public class JdbcRegistrationDao implements RegistrationDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private LobHandler lobHandler;
	private OrgReadDao orgReadDao;
	final static String sql = "INSERT INTO DOCS_STORE (FILE_NAME, FILE_BYTES, INSERT_DT, COMPANY_ID, USER_ID, REF_ID, REQUEST_ID, SOURCE_APP, CATEGORY) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public JdbcRegistrationDao(){
		
	}
	
	public JdbcRegistrationDao( DataSource dataSource, LobHandler lobHandler, OrgReadDao orgReadDao ){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.lobHandler = lobHandler;
		this.orgReadDao = orgReadDao;
	}
	
	private boolean updateRegistrationStatus(String userEmail, int statusCode){
		boolean rowUpdated = false;
		final String sql = "UPDATE CLIENT_LOGIN_INFO SET REGISTRATION_STATUS = :status where EMAIL = :email";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", userEmail);
		paramMap.put("status", statusCode);
		int row = jdbcTemplate.update(sql, paramMap);
		if (row == 1) {
			rowUpdated = true;
		}
		return rowUpdated;
	}
	
	@Override
	public BaseMsg registerSignInInfo(String userEmail, String password,
			String confirmedPassword, int regCode) {
		BaseMsg baseMsg = null;
		final String sql = "INSERT INTO CLIENT_LOGIN_INFO ( EMAIL, PASSWORD, INSERT_DT, REGISTRATION_STATUS) VALUES "
				+ "(:userEmail, :passwd, :insertDt, :regStatus)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userEmail", userEmail);
		paramMap.put("passwd", password);
		paramMap.put("insertDt", new Date());
		paramMap.put("regStatus", regCode);
		int row = jdbcTemplate.update(sql, paramMap);
		if(row == 1) {
			baseMsg = new BaseMsg();
		}
		return baseMsg;
	}

	@Override
	public BaseMsg registerOrgInfo(RegistrationStep2CorpDtlsMsg msg) {
		BaseMsg baseMsg = null;
		final String sql = "INSERT INTO ORGANIZATION_INFO (ACRONYM, COMPANY_NAME, INSERT_DT, UPDATE_DT, UPDATE_BY, CREATED_BY, ORG_TYPE)"
				+ " VALUES (:acro, :companyName, :insertDt, :updateDt, :updateBy, :createdBy, :orgType)";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acro", msg.getAcronym());
		paramMap.put("companyName", msg.getCompName());
		paramMap.put("insertDt", new Date());		
		paramMap.put("updateDt", new Date());		
		paramMap.put("updateBy", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());	// This is HACK to allow admin user to create org obo SELLER org	
		paramMap.put("createdBy", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());		// This is HACK to allow admin user to create org obo SELLER org
		paramMap.put("orgType", msg.getOrgType());		
		int row = jdbcTemplate.update(sql, paramMap);
		if(row == 1){
			
			final String uoMapSql = "INSERT INTO USER_ORGANIZATION_MAP (USER_ID, COMPANY_ID, INSERT_DT) "
					+ " VALUES ( (SELECT USER_ID FROM CLIENT_LOGIN_INFO WHERE EMAIL = :email),"
					+ " (SELECT COMPANY_ID FROM ORGANIZATION_INFO WHERE ACRONYM = :acro), "
					+ " SYSDATE()) ";
			
			Map<String, Object> map = new HashMap<>();
			map.put("email", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());// This is HACK to allow admin user to create org obo SELLER org
			map.put("acro", msg.getAcronym());
			int nestedRow = jdbcTemplate.update(uoMapSql, map);
			if( nestedRow == 1 && updateRegistrationStatus(UserContext.getUserName(), RegistrationStatusEnum.ORG.getCode())){
				baseMsg = new BaseMsg();
			}
		}
		if(msg.getStreet() != null){
			final String orgAddrSql = 
					"INSERT INTO ORGANIZATION_ADDRESS (STREET, CITY, STATE, ZIP_CODE, COUNTRY, INSERT_DT, UPDATE_DT, UPDATE_BY, CREATED_BY, COMPANY_ID)"
					+ " VALUES (:street, :city, :state, :zipCode, 'INDIA', :insertDt, :updateDt, :updateBy, :createdBy, (SELECT COMPANY_ID FROM ORGANIZATION_INFO WHERE ACRONYM = :acro))";
			Map<String, Object> paramMapOrgAddr = new HashMap<>();
			paramMapOrgAddr.put("street", msg.getStreet());
			paramMapOrgAddr.put("city", msg.getCity());
			paramMapOrgAddr.put("state", msg.getState());
			paramMapOrgAddr.put("zipCode", msg.getZipCode());
			paramMapOrgAddr.put("insertDt", new Date());		
			paramMapOrgAddr.put("updateDt", new Date());		
			paramMapOrgAddr.put("updateBy", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());	// This is HACK to allow admin user to create org obo SELLER org	
			paramMapOrgAddr.put("createdBy", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());		// This is HACK to allow admin user to create org obo SELLER org
			paramMapOrgAddr.put("acro", msg.getAcronym());
			jdbcTemplate.update(orgAddrSql, paramMapOrgAddr);
		}
		
		
		
		if(msg.getPhoneNo() != null){
			final String orgPhoneSql = 
					"INSERT INTO ORGANIZATION_CONTACT_INFO (ADDRESS_ID, PHONE_NO, INSERT_DT, UPDATE_DT, UPDATE_BY, CREATED_BY)"
					+ " VALUES ( (SELECT ADDRESS_ID FROM ORGANIZATION_ADDRESS WHERE COMPANY_ID IN (SELECT COMPANY_ID FROM ORGANIZATION_INFO WHERE ACRONYM = :acro)),"
					+ " :phoneNo, :insertDt, :updateDt, :updateBy, :createdBy)";
			Map<String, Object> paramMapPhone = new HashMap<>();
			paramMapPhone.put("phoneNo", msg.getPhoneNo());
			paramMapPhone.put("acro", msg.getAcronym());
			paramMapPhone.put("insertDt", new Date());		
			paramMapPhone.put("updateDt", new Date());		
			paramMapPhone.put("updateBy", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());	// This is HACK to allow admin user to create org obo SELLER org	
			paramMapPhone.put("createdBy", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());		// This is HACK to allow admin user to create org obo SELLER org
			jdbcTemplate.update(orgPhoneSql, paramMapPhone);
		}
		
		
		return baseMsg;		
	}

	@Override
	public BaseMsg registerUserBankInfo(RegistrationStep3UserPersonalDtlsMsg msg) {
		BaseMsg baseMsg = null;
		String userIdSql = "SELECT USER_ID FROM CLIENT_LOGIN_INFO WHERE EMAIL = :email";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", UserContext.getUserName());
		Map<String, Object> userIdSqlResult = jdbcTemplate.queryForObject(userIdSql, params, new ColumnMapRowMapper());
		final String sql = "INSERT INTO SME_USER_INFO (USER_ID, BANK_ACCOUNT_NO, BANK_IFSC_CODE, BANK_NAME, DIRECTOR_FNAME, DIRECTOR_LNAME, PAN_CARD_NO, AADHAR_CARD_ID)"
				+ " VALUES (:userId, :bankAcctNo, :IFSCCd, :bankName, :dirFName, :dirLName, :panCard, :aadharId)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userIdSqlResult.get("USER_ID"));
		paramMap.put("bankAcctNo", msg.getBankAcctNo());		
		paramMap.put("IFSCCd", msg.getIfscCode());
		paramMap.put("bankName", msg.getBankName());
		paramMap.put("dirFName", msg.getDirectorFName());
		paramMap.put("dirLName", msg.getDirectorLName());
		paramMap.put("panCard", msg.getPanCardNo());
		paramMap.put("aadharId", msg.getAadharCardId());
		int row = jdbcTemplate.update(sql, paramMap);
		if( row == 1 && updateRegistrationStatus(UserContext.getUserName(), RegistrationStatusEnum.USER.getCode())){
			baseMsg = new BaseMsg();
		}
		return baseMsg;
		
	}
	
	
	
	
	

	@Override
	public BaseMsg fileUpload(final UploadMessage msg) throws Exception {
		//final int userId = orgReadDao.findUserId(UserContext.getUserName());	
		Map<String, Object> clientDtls = orgReadDao.findOrgAndUserId(UserContext.getUserName());
		final int orgId = (int) clientDtls.get("COMPANY_ID");
		final int userId = (int) clientDtls.get("USER_ID");
		final LobCreator lobCreator = lobHandler.getLobCreator();
		final byte [] bytes = msg.getFile().getBytes();
		jdbcTemplate.getJdbcOperations().update( new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {

				final PreparedStatement ps = conn.prepareStatement(sql);
				try {
					ps.setString(1, msg.getFileName());
					lobCreator.setBlobAsBytes(ps, 2, bytes);
					ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
					ps.setInt(4, orgId);
					ps.setInt(5, userId);			
					ps.setString(6, UUIDGenerator.newRefId());
					ps.setString(7, UUID.randomUUID().toString());
					ps.setInt(8, 0);
					ps.setString(9, msg.getCategory());
				} catch (SQLException e) {
					throw e;
				}
				return ps;
			}
		});
		lobCreator.close();
		BaseMsg response = new BaseMsg();	
		response.addInfoMsg("File uploaded successfully", HttpStatus.OK.value());
		return response;
	}

	@Override
	public ListMsg<SupportDocDtls> summary(String acronym) {
		int orgId = 0;
		if ( acronym == null ){
			orgId = orgReadDao.findOrgIdByEmail(UserContext.getUserName());
		}else{
			Map<String, Object> orgInfo = orgReadDao.findOrgId(acronym);
			orgId = (int) orgInfo.get("COMPANY_ID");
		}
		final String sql = "SELECT DS.FILE_NAME, DS.REF_ID, DS.INSERT_DT, DS.CATEGORY, CLI.EMAIL FROM DOCS_STORE DS"
				+ " JOIN CLIENT_LOGIN_INFO CLI"
				+ " ON CLI.USER_ID = DS.USER_ID"
				+ " WHERE DS.COMPANY_ID = :orgId";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orgId", orgId);
		List<SupportDocDtls> list = jdbcTemplate.query(sql, paramMap, new SupportDocRowMapper());
		ListMsg<SupportDocDtls> result = new ListMsg<>(list);
		return result;
	}

	
	private class SupportDocRowMapper implements RowMapper<SupportDocDtls>{

		@Override
		public SupportDocDtls mapRow(ResultSet rs, int arg1)
				throws SQLException {
			SupportDocDtls rec = new SupportDocDtls();
			rec.setFileName(rs.getString("FILE_NAME"));
			rec.setRefId(rs.getString("REF_ID"));
			rec.setTimest(rs.getDate("INSERT_DT"));
			rec.setUser(rs.getString("EMAIL"));
			rec.setCateg(rs.getString("CATEGORY"));
			return rec;
		}
		
	}


	@Override
	public RegistrationStep2CorpDtlsMsg fetchOrgInfo(String acronym) {
		final String sql = " SELECT OI.ACRONYM, OI.COMPANY_NAME, OI.ORG_TYPE, OA.STREET, OA.CITY, OA.STATE, OA.ZIP_CODE,"
				+ " OA.COUNTRY, OCI.PHONE "
				+ " FROM ORGANIZATION_INFO OI "
				+ " JOIN ORGANIZATION_ADDRESS OA "
				+ " ON OI.COMPANY_ID = OA.COMPANY_ID "
				+ " JOIN ORGANIZATION_CONTACT_INFO OCI "
				+ " ON OA.ADDRESS_ID = OCI.ADDRESS_ID "
				+ " WHERE ACRONYM = :acronym";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acronym", acronym);
		RegistrationStep2CorpDtlsMsg result = jdbcTemplate.queryForObject(sql, paramMap, new RegistrationStep2CorpDtlsMsgRowMapper());
		return result;
	}

	private class RegistrationStep2CorpDtlsMsgRowMapper implements RowMapper<RegistrationStep2CorpDtlsMsg>{

		@Override
		public RegistrationStep2CorpDtlsMsg mapRow(ResultSet rs, int arg1)
				throws SQLException {
			RegistrationStep2CorpDtlsMsg result = new RegistrationStep2CorpDtlsMsg();
			result.setAcronym(rs.getString("ACRONYM"));
			result.setCompName(rs.getString("COMPANY_NAME"));
			result.setOrgType(rs.getString("ORG_TYPE"));
			result.setStreet(rs.getString("STREET"));
			result.setCity(rs.getString("CITY"));
			result.setState(rs.getString("STATE"));
			result.setZipCode(rs.getString("ZIP_CODE"));
			result.setPhoneNo(rs.getString("PHONE"));
			return result;
		}
		
	}
	
	@Override
	public RegistrationStep3UserPersonalDtlsMsg fetchUserBankInfo(String acronym) {
		
		final String sql = "SELECT SUI.BANK_ACCOUNT_NO, SUI.BANK_IFSC_CODE, SUI.BANK_NAME, SUI.DIRECTOR_FNAME, "
				+ " SUI.DIRECTOR_LNAME, SUI.PAN_CARD_NO, SUI.AADHAR_CARD_ID "
				+ " FROM SME_USER_INFO SUI JOIN "
				+ " USER_ORGANIZATION_MAP UOM ON "
				+ " SUI.USER_ID = UOM.USER_ID "
				+ " JOIN ORGANIZATION_INFO OI "
				+ " ON OI.COMPANY_ID = UOM.COMPANY_ID "
				+ " WHERE OI.ACRONYM = :acronym";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acronym", acronym);
		RegistrationStep3UserPersonalDtlsMsg result = jdbcTemplate.queryForObject(sql, paramMap, new RegistrationStep3UserPersonalDtlsMsgRowMapper());
		return result;
	}
	
	private class RegistrationStep3UserPersonalDtlsMsgRowMapper implements RowMapper<RegistrationStep3UserPersonalDtlsMsg>{

		@Override
		public RegistrationStep3UserPersonalDtlsMsg mapRow(ResultSet rs, int arg1)
				throws SQLException {
			RegistrationStep3UserPersonalDtlsMsg result = new RegistrationStep3UserPersonalDtlsMsg();
			result.setBankName(rs.getString("BANK_NAME"));
			result.setBankAcctNo(rs.getString("BANK_ACCOUNT_NO"));
			result.setAadharCardId(rs.getString("AADHAR_CARD_ID"));
			result.setDirectorFName(rs.getString("DIRECTOR_FNAME"));
			result.setDirectorLName(rs.getString("DIRECTOR_LNAME"));
			result.setIfscCode(rs.getString("BANK_IFSC_CODE"));
			result.setPanCardNo(rs.getString("PAN_CARD_NO"));
			return result;
		}
		
	}


	@Override
	public BaseMsg updateRegisteredEmail(RegistrationStep1SignInDtlsMsg msg) {

		BaseMsg baseMsg = null;
		final String sql = "UPDATE CLIENT_LOGIN_INFO SET UPDATE_DT = :updatedt, PASSWORD = :passwd, REGISTRATION_STATUS = :regStatus WHERE EMAIL = :email ";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", msg.getEmail());
		paramMap.put("passwd", msg.getPasswd());
		paramMap.put("regStatus", RegistrationStatusEnum.LOGIN.getCode());
		paramMap.put("updatedt", new Timestamp(System.currentTimeMillis()));
		int row = jdbcTemplate.update(sql, paramMap);
		if(row == 1) {
			baseMsg = new BaseMsg();
		}
		return baseMsg;
	
	}
	
	
}

