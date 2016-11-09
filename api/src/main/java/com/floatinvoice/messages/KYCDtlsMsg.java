package com.floatinvoice.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="compInfo")
@XmlAccessorType(value=XmlAccessType.PROPERTY)
public class KYCDtlsMsg extends BaseMsg{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String regCompName;
	private String website;
	private String directorName;
	private String directorContactNo;
	private String directorEmailAddress;
	private String contactPersonName;
	private String contactPersonContactNo;
	private String contactPersonEmail;
	private RegistrationStep2CorpDtlsMsg regOffice;
	private RegistrationStep2CorpDtlsMsg corpOffice;
	private TradeLicense tradeLicense;
	private RegistrationStep3UserPersonalDtlsMsg bankDtls;
	private CorpInfo corpInfo;
	
	public String getRegCompName() {
		return regCompName;
	}
	public void setRegCompName(String regCompName) {
		this.regCompName = regCompName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getDirectorContactNo() {
		return directorContactNo;
	}
	public void setDirectorContactNo(String directorContactNo) {
		this.directorContactNo = directorContactNo;
	}
	public String getDirectorEmailAddress() {
		return directorEmailAddress;
	}
	public void setDirectorEmailAddress(String directorEmailAddress) {
		this.directorEmailAddress = directorEmailAddress;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactPersonContactNo() {
		return contactPersonContactNo;
	}
	public void setContactPersonContactNo(String contactPersonContactNo) {
		this.contactPersonContactNo = contactPersonContactNo;
	}
	public String getContactPersonEmail() {
		return contactPersonEmail;
	}
	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}
	public RegistrationStep2CorpDtlsMsg getRegOffice() {
		return regOffice;
	}
	public void setRegOffice(RegistrationStep2CorpDtlsMsg regOffice) {
		this.regOffice = regOffice;
	}
	public RegistrationStep2CorpDtlsMsg getCorpOffice() {
		return corpOffice;
	}
	public void setCorpOffice(RegistrationStep2CorpDtlsMsg corpOffice) {
		this.corpOffice = corpOffice;
	}
	public TradeLicense getTradeLicense() {
		return tradeLicense;
	}
	public void setTradeLicense(TradeLicense tradeLicense) {
		this.tradeLicense = tradeLicense;
	}
	public RegistrationStep3UserPersonalDtlsMsg getBankDtls() {
		return bankDtls;
	}
	public void setBankDtls(RegistrationStep3UserPersonalDtlsMsg bankDtls) {
		this.bankDtls = bankDtls;
	}
	public CorpInfo getCorpInfo() {
		return corpInfo;
	}
	public void setCorpInfo(CorpInfo corpInfo) {
		this.corpInfo = corpInfo;
	}
	
	
}
