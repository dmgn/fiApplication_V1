package com.floatinvoice.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.floatinvoice.messages.ProductDtls;



@Repository
public class JdbcFIProductDao implements FIProductDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public JdbcFIProductDao(){
		
	}
	
	public JdbcFIProductDao(DataSource dataSource){
		 jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
	}


	@Override
	public ProductDtls findProductByRefId(String refId) {
		final String sql = "SELECT * FROM FI_PRODUCTS WHERE REF_ID = :refId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("refId", refId);
		ProductDtls result = jdbcTemplate.queryForObject(sql, paramMap, new FIProductRowMapper());
		return result;
	}
	
	@Override
	public List<ProductDtls> findAllProducts() {
		final String sql = "SELECT * FROM FI_PRODUCTS";
		List<ProductDtls> result = jdbcTemplate.query(sql, new FIProductRowMapper());
		return result;
	}

	private class FIProductRowMapper implements RowMapper<ProductDtls>{

		@Override
		public ProductDtls mapRow(ResultSet rs, int arg1) throws SQLException {
			ProductDtls p = new ProductDtls();
			p.setProductDescription(rs.getString("DESCRIPTION"));
			p.setProductId(rs.getInt("PRODUCT_ID"));
			p.setProductOwnerAcro(rs.getString("PRODUCT_OWNER_ACRO"));
			p.setProductType(rs.getInt("PRODUCT_TYPE"));
			p.setProductName(rs.getString("PRODUCT_NAME"));
			p.setRefId(rs.getString("REF_ID"));
			return p;
		}
		
	}
}
