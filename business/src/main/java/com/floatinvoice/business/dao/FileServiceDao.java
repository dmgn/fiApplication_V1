package com.floatinvoice.business.dao;

import java.util.List;

import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.SupportDocDtls;
import com.floatinvoice.messages.UploadMessage;

public interface FileServiceDao {

	byte [] download(String refId);
	
	byte [] downloadSupportDocs(String refId);
	
	byte [] viewLoanAgreement(String loanRefId);
	
	BaseMsg uploadLenderAgreement(UploadMessage msg) throws Exception;
	
	BaseMsg uploadInvoiceTemplate(UploadMessage msg) throws Exception;
	
	byte [] downloadLenderAgreement(String lenderAcronym, String refId);
	
	List<SupportDocDtls> summarySupportDocs(int companyId, int userId); 
	
	List<SupportDocDtls> summarySupportDocs(String acronym); 
	
	byte[] downloadInvoiceTemplate(String refId);
	
	SupportDocDtls invoiceTemplateMetaData(String category);
}
