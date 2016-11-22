package com.floatinvoice.business;

import java.util.List;
import java.util.Map;

import com.floatinvoice.business.dao.EnquiryDao;
import com.floatinvoice.business.dao.FIApplicationDao;
import com.floatinvoice.business.dao.FIProductDao;
import com.floatinvoice.business.dao.OrgReadDao;
import com.floatinvoice.common.UserContext;
import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.BuyerDtlsMsg;
import com.floatinvoice.messages.KYCDtlsMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.ProductDtls;

public class FIApplicationServiceImpl implements FIApplicationService{

	EnquiryDao enquiryDao;
	OrgReadDao orgReadDao;
	FIApplicationDao fiApplicationDao;
	FIProductDao fiProductDao;
	RegistrationService registrationService;
	BankService bankService;
	EnquiryService enquiryService;
	
	public FIApplicationServiceImpl(){
		
	}
	
	public FIApplicationServiceImpl(EnquiryDao enquiryDao, OrgReadDao orgReadDao, FIApplicationDao fiApplicationDao,
			FIProductDao fiProductDao, RegistrationService registrationService,
			BankService bankService, EnquiryService enquiryService){
		this.enquiryDao = enquiryDao;
		this.orgReadDao = orgReadDao;
		this.fiApplicationDao = fiApplicationDao;
		this.fiProductDao = fiProductDao;
		this.registrationService = registrationService;
		this.bankService = bankService;
		this.enquiryService = enquiryService;
	}

	@Override
	public List<AppDtlsMsg> viewAllApplications(String acro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppDtlsMsg viewOneApplication(String refId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMsg saveApplication(AppDtlsMsg appDetails) {
		Map<String, Object> org = orgReadDao.findOrgId(appDetails.getAcro());
		ProductDtls productDtls = fiProductDao.findProductByRefId(appDetails.getProductRefId());
		int companyId = (int) org.get("company_id");
		Map<String, Object> orgInfo = orgReadDao.findOrgAndUserId(UserContext.getUserName());
		return fiApplicationDao.saveApplication(appDetails, companyId, (int)orgInfo.get("USER_ID"), productDtls.getProductId());
	}

	@Override
	public BaseMsg editApplication(AppDtlsMsg appDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KYCDtlsMsg viewOneKYCApplication(String acro, String refId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMsg saveBuyerDetails(BuyerDtlsMsg buyerDtls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListMsg<BuyerDtlsMsg> viewBuyerDetails(String acronym,
			String appRefId) {
		
		return null;
	}

}
