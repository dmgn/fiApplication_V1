package com.floatinvoice.business;

import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.ProductDtls;

public interface ProductService {
	
	ListMsg<ProductDtls> viewAllProducts(String acro);
	ProductDtls viewOneProduct(String refId);
	BaseMsg saveProduct( ProductDtls appDetails );
	BaseMsg editProduct( ProductDtls appDetails );

}
