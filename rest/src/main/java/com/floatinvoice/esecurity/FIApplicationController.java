package com.floatinvoice.esecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.floatinvoice.business.FIApplicationService;
import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.BuyerDtlsMsg;
import com.floatinvoice.messages.KYCDtlsMsg;
import com.floatinvoice.messages.ListMsg;

@Controller
public class FIApplicationController {

	@Autowired
	FIApplicationService fiApplicationService;
	
	
	
	@RequestMapping(value = { "/viewKYCApp"}, method = RequestMethod.GET)
	public  ResponseEntity<KYCDtlsMsg> viewKYCApplication(@RequestParam(value="refId", required=true) String refId,
			@RequestParam(value="acro", required=true) String acro){
		return new ResponseEntity<KYCDtlsMsg>(fiApplicationService.viewOneKYCApplication(acro, refId), HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/saveApp"}, method = RequestMethod.POST)
	public  ResponseEntity<BaseMsg> saveApplication( @RequestBody AppDtlsMsg appDetails ){
		return new ResponseEntity<BaseMsg>( fiApplicationService.saveApplication(appDetails), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = { "/saveBuyerDtls"}, method = RequestMethod.POST)
	public  ResponseEntity<BaseMsg> saveBuyerDtls( @RequestBody BuyerDtlsMsg buyerDtls ){
		return new ResponseEntity<BaseMsg>( fiApplicationService.saveBuyerDetails(buyerDtls), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = { "/viewBuyerDtls"}, method = RequestMethod.GET)
	public  ResponseEntity<ListMsg<BuyerDtlsMsg>> viewBuyerDtls(@RequestParam(value="refId", required=true) String appRefId,
			@RequestParam(value="acro", required=true) String acro){
		return new ResponseEntity<ListMsg<BuyerDtlsMsg>>(fiApplicationService.viewBuyerDetails(acro, appRefId), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = { "/editApp"}, method = RequestMethod.POST)
	public  ResponseEntity<BaseMsg> editApplication( @RequestBody AppDtlsMsg appDetails ){
		return null;
	}
	
	@RequestMapping(value = { "/viewApp"}, method = RequestMethod.GET)
	public  ResponseEntity<AppDtlsMsg> viewApplication(@RequestParam(value="refId", required=true) String refId){
		return null;
	}
	
	@RequestMapping(value = { "/viewAllApps"}, method = RequestMethod.GET)
	public  ResponseEntity<ListMsg<AppDtlsMsg>> viewAllApplications(@RequestParam(value="acro", required=true) String acro){
		return null;
	}
	
}
