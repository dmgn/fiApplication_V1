package com.floatinvoice.business;

import java.util.List;

import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.KYCDtlsMsg;


public interface FIApplicationService {

	List<AppDtlsMsg> viewAllApplications(String acro);
	AppDtlsMsg viewOneApplication(String refId);
	BaseMsg saveApplication( AppDtlsMsg appDetails );
	BaseMsg editApplication( AppDtlsMsg appDetails );
	
	KYCDtlsMsg viewOneKYCApplication(String acro, String regId);
}
