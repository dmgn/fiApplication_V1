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
import com.floatinvoice.messages.ListMsg;

@Controller
public class FIApplicationController {

	@Autowired
	FIApplicationService fiApplicationService;
	
	@RequestMapping(value = { "/saveApp"}, method = RequestMethod.POST)
	public  ResponseEntity<BaseMsg> saveApplication( @RequestBody AppDtlsMsg appDetails ){
		return new ResponseEntity<BaseMsg>( fiApplicationService.saveApplication(appDetails), HttpStatus.OK);
		
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
