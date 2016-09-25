package com.floatinvoice.business.dao;

import java.util.List;

import com.floatinvoice.messages.ProductDtls;

public interface FIProductDao {

	ProductDtls findProductByRefId(String refId);
	List<ProductDtls> findAllProducts();
}
