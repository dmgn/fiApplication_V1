package com.floatinvoice.business;

import java.util.List;
import java.util.Map;

import com.floatinvoice.business.dao.EnquiryDao;
import com.floatinvoice.business.dao.FIApplicationDao;
import com.floatinvoice.business.dao.FIProductDao;
import com.floatinvoice.business.dao.OrgReadDao;
import com.floatinvoice.common.EnquiryStatusEnum;
import com.floatinvoice.common.UserContext;
import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.EnquiryFormMsg;
import com.floatinvoice.messages.KYCDtlsMsg;
import com.floatinvoice.messages.ProductDtls;
import com.floatinvoice.messages.RegistrationStep2CorpDtlsMsg;
import com.floatinvoice.messages.RegistrationStep3UserPersonalDtlsMsg;

public class FIApplicationServiceImpl implements FIApplicationService {

	EnquiryDao enquiryDao;
	OrgReadDao orgReadDao;
	FIApplicationDao fiApplicationDao;
	FIProductDao fiProductDao;
	RegistrationService regService;
	BankService bankService;
	EnquiryService enquiryService;
	
	
	
	public FIApplicationServiceImpl(){
		
	}
	
	public FIApplicationServiceImpl( EnquiryDao enquiryDao, OrgReadDao orgReadDao, FIApplicationDao fiApplicationDao,
			FIProductDao fiProductDao,
			RegistrationService regService,
			BankService bankService,
			EnquiryService enquiryService){
		this.enquiryDao = enquiryDao;
		this.orgReadDao = orgReadDao;
		this.fiApplicationDao = fiApplicationDao;
		this.fiProductDao = fiProductDao;
		this.regService = regService;
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
		Map<String, Object> orgInfo = orgReadDao.findOrgAndUserId(UserContext.getUserName());
		ProductDtls productDtls = fiProductDao.findProductByRefId(appDetails.getRefId());
		return fiApplicationDao.saveApplication(appDetails, (Integer)orgInfo.get("COMPANY_ID"), 
				(Integer)orgInfo.get("USER_ID"), productDtls.getProductId());
		

	}

	@Override
	public BaseMsg editApplication(AppDtlsMsg appDetails) {
		return null;
	}

	@Override
	public KYCDtlsMsg viewOneKYCApplication(String acro, String enqRefId) {
		KYCDtlsMsg response = new KYCDtlsMsg();
		RegistrationStep2CorpDtlsMsg regOffice = regService.fetchOrgInfo(acro);
		response.setRegOffice(regOffice);
		RegistrationStep3UserPersonalDtlsMsg bankDtls = regService.fetchUserBankInfo(acro);
		response.setBankDtls(bankDtls);
		EnquiryFormMsg enquiry = enquiryService.viewOneEnquiry(enqRefId);
		response.setContactPersonContactNo(enquiry.getPhone());
		response.setContactPersonEmail(enquiry.getEmail());
		response.setContactPersonName(enquiry.getContactName());
		response.setRegCompName(regOffice.getCompName());
		//response.setCo
		return response;
	}

}
