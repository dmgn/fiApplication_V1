package com.floatinvoice.business.dao;

import java.util.List;

import com.floatinvoice.messages.LoanAgreementTemplateDtls;

public interface LoanAgreementDao {

	List<LoanAgreementTemplateDtls> fetchPendingAgreementsForApprovedLoans();
	
	void updateAgreement(byte [] bytes, int loanId);
	
	List<String> fetchTemplateParams(String refId);
}
