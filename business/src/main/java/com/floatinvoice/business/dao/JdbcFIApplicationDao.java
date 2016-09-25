package com.floatinvoice.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.floatinvoice.common.UUIDGenerator;
import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;
@Repository
public class JdbcFIApplicationDao implements FIApplicationDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public JdbcFIApplicationDao(){
		
	}
	
	public JdbcFIApplicationDao( DataSource dataSource ){
		jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
	}
	
	@Override
	public List<AppDtlsMsg> viewAllApplications(int companyId) {
		final String sql = "SELECT * FROM FI_APPLICATION_INFO FAI";
		//jdbcTemplate.query(sql, null);
		return null ;
	}

	@Override
	public AppDtlsMsg viewOneApplication(String refId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMsg saveApplication(final AppDtlsMsg appDetails, final int companyId, final int userId, final int productId) {
		final String sql = "INSERT INTO FI_APPLICATION_INFO "
				+ "(PRODUCT_ID, ENQUIRY_ID, COMPANY_ID, USER_ID, APPLICATION_DT, REF_ID, REQ_ID, SOURCE) VALUES "
				+ " (?, ?, ?, ?, ?, ?, ?, ?) ";
		
		jdbcTemplate.getJdbcOperations().update( new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {

				final PreparedStatement ps = conn.prepareStatement(sql);
				try {
					ps.setInt(1, productId);
					ps.setInt(2, appDetails.getEnquiryId());
					ps.setInt(3, companyId);
					ps.setInt(4, userId);					
					ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
					ps.setString(6, UUIDGenerator.newRefId());
					ps.setString(7, UUID.randomUUID().toString());
					ps.setInt(8, 0);
				} catch (SQLException e) {
					throw e;
				}
				return ps;
			}
		});
		
		BaseMsg response = new BaseMsg();	
		response.addInfoMsg("Application saved successfully", HttpStatus.OK.value());
		return response;
	}

	@Override
	public BaseMsg editApplication(AppDtlsMsg appDetails) {
		return null;
	}

}
