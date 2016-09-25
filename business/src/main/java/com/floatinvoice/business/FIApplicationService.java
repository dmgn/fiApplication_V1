package com.floatinvoice.business;

import java.util.List;

import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;


public interface FIApplicationService {

	List<AppDtlsMsg> viewAllApplications(String acro);
	AppDtlsMsg viewOneApplication(String refId);
	BaseMsg saveApplication( AppDtlsMsg appDetails );
	BaseMsg editApplication( AppDtlsMsg appDetails );
}
