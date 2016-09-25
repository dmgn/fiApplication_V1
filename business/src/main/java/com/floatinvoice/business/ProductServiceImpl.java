package com.floatinvoice.business;

import com.floatinvoice.business.dao.FIProductDao;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.ProductDtls;

public class ProductServiceImpl implements ProductService {

	FIProductDao fiProductDao;
	
	public ProductServiceImpl(){
		
	}
	
	public ProductServiceImpl(FIProductDao fiProductDao){
		this.fiProductDao = fiProductDao;
	}


	@Override
	public ListMsg<ProductDtls> viewAllProducts(String acro) {
		return new ListMsg<>(fiProductDao.findAllProducts());
	}

	@Override
	public ProductDtls viewOneProduct(String refId) {
		return fiProductDao.findProductByRefId(refId);
	}

	@Override
	public BaseMsg saveProduct(ProductDtls appDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMsg editProduct(ProductDtls appDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
