package com.floatinvoice.esecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.floatinvoice.business.ProductService;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.ProductDtls;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = { "/products/viewAll"}, method = RequestMethod.GET)
    public ResponseEntity<ListMsg<ProductDtls>> viewAllProducts(@RequestParam(value="acro", required=true) String acro) throws Exception {
        return new ResponseEntity<>(productService.viewAllProducts(acro), HttpStatus.OK);
    }
	
}
