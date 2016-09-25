package com.floatinvoice.business.dao;

import java.util.List;

import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;

public interface FIApplicationDao {

	List<AppDtlsMsg> viewAllApplications(int companyId);
	AppDtlsMsg viewOneApplication(String refId);
	BaseMsg saveApplication( AppDtlsMsg appDetails, int companyId, int userId, int productId );
	BaseMsg editApplication( AppDtlsMsg appDetails );
	
}
