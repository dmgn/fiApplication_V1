package com.floatinvoice.business;

import java.util.List;
import java.util.Map;

import com.floatinvoice.business.dao.EnquiryDao;
import com.floatinvoice.business.dao.OrgReadDao;
import com.floatinvoice.common.EnquiryStatusEnum;
import com.floatinvoice.common.OrgType;
import com.floatinvoice.common.UserContext;
import com.floatinvoice.common.Utility;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.EnquiryFormMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.OrgDtlsMsg;
import com.floatinvoice.messages.RegistrationStep1SignInDtlsMsg;
import com.floatinvoice.messages.RegistrationStep2CorpDtlsMsg;

public class EnquiryServiceImpl implements EnquiryService {
	
	private EnquiryDao enqDao;
	
	private EmailService emailService;
	
	private RegistrationService regService;
	
	private OrgReadDao orgReadDao;
	
	public EnquiryServiceImpl(){
		
	}
	
	public EnquiryServiceImpl(EnquiryDao enqDao, EmailService emailService, RegistrationService regService,
			OrgReadDao orgReadDao){
		this.enqDao = enqDao;
		this.emailService = emailService;
		this.regService = regService;	
		this.orgReadDao = orgReadDao;
	}
	
	@Override
	public ListMsg<EnquiryFormMsg> viewEnquiry(int enqStatus, String orgType) {
		return new ListMsg<>(enqDao.viewEnquiries(enqStatus, orgType));
	}

	@Override
	public BaseMsg updateEnquiry(int enqStatus, String refId) {
		int result = enqDao.updateEnquiry(enqStatus, refId);
		BaseMsg response = new BaseMsg();
		if(result > 0){
			response.addInfoMsg("Enquiry Updated Successfully", 0);
		}else{
			response.addInfoMsg("Enquiry Update Failed", -1);
		}
		return response;
	}

	@Override
	public BaseMsg sendAcctSetupNotification(String email, String refId, String password) {
		//EnquiryFormMsg enquiry = enqDao.findOneEnquiry(refId);
		int result = enqDao.updateEnquiry(EnquiryStatusEnum.QUALIFIED.getCode(), refId);
		BaseMsg response = new BaseMsg();
		if(result > 0){
			emailService.sendEmail("Float Invoice Account Setup", email, 
					new StringBuffer(String.format("Please login to http://54.210.238.169:8080/floatinvoice/loginpage using the credentials in the email."
							+ " Login Id is %s and Password is %s", email, password)));
			response.addInfoMsg("Enquiry Updated Successfully", 0);
		}else{
			response.addInfoMsg("Enquiry Update Failed", -1);
		}
		return response;
	}

	@Override
	public BaseMsg notifyFloatInvoice(String user, String acronym, String refId) {
		EnquiryFormMsg enquiry = enqDao.findOneEnquiry(refId);
		int result = enqDao.updateEnquiry(EnquiryStatusEnum.STAGED.getCode(), refId);
		Map<String, Object> org = orgReadDao.findOrgAndUserId(UserContext.getUserName());
		mapEnquiryToOrgSetup(enquiry.getRefId(), enquiry.getEnqId(), (Integer)org.get("COMPANY_ID"), 
				(Integer)org.get("USER_ID"));
		BaseMsg response = new BaseMsg();
		if(result > 0){			
			emailService.sendEmail("Documents Uploaded - " + enquiry.getContactName(), "support@floatinvoice.com", new StringBuffer("All requested documents are uploaded for enquiry reference id "+enquiry.getRefId()));
			response.addInfoMsg("Enquiry Updated Successfully", 0);
		}else{
			response.addInfoMsg("Enquiry Update Failed", -1);
		}
		return null;
	}

	@Override
	public BaseMsg sendThirdPartyNotification(String refId) {
		EnquiryFormMsg enquiry = enqDao.findOneEnquiry(refId);
		int result = enqDao.updateEnquiry(EnquiryStatusEnum.RELEASED.getCode(), refId);
		BaseMsg response = new BaseMsg();
		if(result > 0){
			List<Integer> nbfcOrgIds = orgReadDao.findAllNBFCS();
			
			for(Integer nbfcOrgId : nbfcOrgIds){
				enqDao.saveEnquiryNotifications(enquiry, nbfcOrgId);
				emailService.sendEmail("Prospect from Float Invoice", "support@floatinvoice.com", new StringBuffer("Please login to floatinvoice and check out the prospect details.")); // change the email
			}
			response.addInfoMsg("Enquiry Updated Successfully", 0);
		}else{
			response.addInfoMsg("Enquiry Update Failed", -1);
		}
		return response;
	}

	@Override
	public BaseMsg setupTempAcct(OrgDtlsMsg orgDtls) {
		EnquiryFormMsg enquiry = enqDao.findOneEnquiry(orgDtls.getRefId());
		RegistrationStep1SignInDtlsMsg signInMsg = new RegistrationStep1SignInDtlsMsg();
		signInMsg.setEmail(enquiry.getEmail());
		signInMsg.setPasswd(Utility.passwdString(8));
		signInMsg.setConfirmPasswd(Utility.passwdString(8));
		BaseMsg signInMsgResp = regService.registerSignInInfo(signInMsg);		
		RegistrationStep2CorpDtlsMsg corpDtlsMsg = new RegistrationStep2CorpDtlsMsg();
		corpDtlsMsg.setAcronym(orgDtls.getAcro()/*Utility.acroRandomString(8)*/);
		Utility.set(corpDtlsMsg, "compName", orgDtls.getOrgName());
		Utility.set(corpDtlsMsg, "orgType", OrgType.SELLER.getText());
		Utility.set(corpDtlsMsg, "user", enquiry.getEmail());
		BaseMsg corpDtlsMsgResp = regService.registerOrgInfo(corpDtlsMsg);		
		Map<String, Object> orgInfoObject = orgReadDao.findOrgAndUserId(enquiry.getEmail());
		int userId = Integer.class.cast(orgInfoObject.get("USER_ID"));
		int companyId = Integer.class.cast(orgInfoObject.get("COMPANY_ID"));
		mapEnquiryToOrgSetup(orgDtls.getRefId(), enquiry.getEnqId(), companyId, userId);
		BaseMsg acctSetupResp = null;
		if(signInMsgResp.getSystemMessages().getInfo().size() > 0 &&
				corpDtlsMsgResp.getSystemMessages().getInfo().size() > 0){
			acctSetupResp = sendAcctSetupNotification(enquiry.getEmail(), orgDtls.getRefId(), signInMsg.getPasswd());
		}		
		return acctSetupResp;
	}

	@Override
	public ListMsg<EnquiryFormMsg> viewEnquiryNotifications(String acronym) {
		Map<String, Object> org = orgReadDao.findOrgId(acronym);
		Integer orgId = (Integer) org.get("COMPANY_ID");
		return new ListMsg<>(enqDao.viewEnquiryNotifications(orgId));
	}

	@Override
	public void mapEnquiryToOrgSetup(String enquiryRefId, int enquiryId, int orgId, int userId) {
		enqDao.mapEnquiryToOrgSetup(enquiryRefId, enquiryId, orgId, userId);
	}

	@Override
	public EnquiryFormMsg viewStagedEnquiry(int enqStatus, String refId,
			String companyId) {
		return enqDao.viewStagedEnquiry(enqStatus, refId, companyId);
	}

	@Override
	public BaseMsg qualifyEnquiry(String refId, String email) {
		return enqDao.qualifyEnquiry(refId, email);
	}

	@Override
	public BaseMsg rejectEnquiry(String refId, String email) {
		return enqDao.rejectEnquiry(refId, email);
	}

	@Override
	public EnquiryFormMsg viewOneEnquiry(String refId) {
		return enqDao.findOneEnquiry(refId);
	}

}
