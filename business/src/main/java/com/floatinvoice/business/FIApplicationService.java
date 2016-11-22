package com.floatinvoice.business;

import java.util.List;

import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.BuyerDtlsMsg;
import com.floatinvoice.messages.KYCDtlsMsg;
import com.floatinvoice.messages.ListMsg;


public interface FIApplicationService {

	List<AppDtlsMsg> viewAllApplications(String acro);
	AppDtlsMsg viewOneApplication(String refId);
	BaseMsg saveApplication( AppDtlsMsg appDetails );
	BaseMsg editApplication( AppDtlsMsg appDetails );
	KYCDtlsMsg viewOneKYCApplication(String acro, String refId);
	BaseMsg saveBuyerDetails( BuyerDtlsMsg buyerDtls );
	ListMsg<BuyerDtlsMsg> viewBuyerDetails(String acronym, String appRefId);
}
