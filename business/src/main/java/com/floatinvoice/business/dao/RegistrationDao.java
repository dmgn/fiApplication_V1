package com.floatinvoice.business.dao;

import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.RegistrationStep1SignInDtlsMsg;
import com.floatinvoice.messages.RegistrationStep2CorpDtlsMsg;
import com.floatinvoice.messages.RegistrationStep3UserPersonalDtlsMsg;
import com.floatinvoice.messages.SupportDocDtls;
import com.floatinvoice.messages.UploadMessage;

public interface RegistrationDao {

	BaseMsg registerSignInInfo(String userEmail, String password, String confirmedPassword, int regCode);
	
	BaseMsg registerOrgInfo(RegistrationStep2CorpDtlsMsg msg);

	RegistrationStep2CorpDtlsMsg fetchOrgInfo(String acronym);
	
	BaseMsg registerUserBankInfo (RegistrationStep3UserPersonalDtlsMsg msg);
	
	RegistrationStep3UserPersonalDtlsMsg fetchUserBankInfo(String acronym);
	
	BaseMsg fileUpload(UploadMessage msg) throws Exception;
	
	ListMsg<SupportDocDtls> summary(String acronym);

	BaseMsg updateRegisteredEmail(RegistrationStep1SignInDtlsMsg msg);
}
