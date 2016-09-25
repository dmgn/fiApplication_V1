package com.floatinvoice.messages;

import javax.xml.bind.annotation.XmlElement;

public class AppDtlsMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="applicationId")
	private int applicationId;
	
	@XmlElement(name="productRefId")
	private String productRefId; 

	@XmlElement(name="enquiryId")
	private int enquiryId;
	
	@XmlElement(name="acro")
	private String acro;

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getProductRefId() {
		return productRefId;
	}

	public void setProductRefId(String productRefId) {
		this.productRefId = productRefId;
	}

	public int getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getAcro() {
		return acro;
	}

	public void setAcro(String acro) {
		this.acro = acro;
	}
	
	
}
