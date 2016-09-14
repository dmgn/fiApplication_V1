package com.floatinvoice.messages;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LoanAgreementTemplateDtls extends BaseMsg{

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	
	public Date loanDispatchDt;
	
	public Date loanCloseDt;

	public int loanId;
	
	public String loanAmount;
	
	public String day;
	
	public String month;
	
	public String year;
	
	public String signedDay;
	
	public String signedMonth;
	
	public String signedYear;
	
	public String borrowerCompanyName;
	
	public int smeOrgId;
	
	public int smeCtpyOrgId;
	
	public int lenderOrgId;
	
	public String lenderCompanyName;
	
	public InputStream loanAgreementTemplate;
	
	public InputStream loanAgreement;
	
	public Map<String, String> paramMap = new LinkedHashMap<>();
	
	public List<String> companyDirectors = new LinkedList<>();
	
	public String agreementRefId;
	
	public String street;
	
	public String city;
	
	public String pinCode;
	
	public String state;
	
	public String country;

	public String loanCloseDtDisplay;
	
	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBorrowerCompanyName() {
		return borrowerCompanyName;
	}

	public void setBorrowerCompanyName(String borrowerCompanyName) {
		this.borrowerCompanyName = borrowerCompanyName;
	}

	public List<String> getCompanyDirectors() {
		return companyDirectors;
	}

	public void setCompanyDirectors(List<String> companyDirectors) {
		this.companyDirectors = companyDirectors;
	}

	public String getSignedDay() {
		return signedDay;
	}

	public void setSignedDay(String signedDay) {
		this.signedDay = signedDay;
	}

	public String getSignedMonth() {
		return signedMonth;
	}

	public void setSignedMonth(String signedMonth) {
		this.signedMonth = signedMonth;
	}

	public String getSignedYear() {
		return signedYear;
	}

	public void setSignedYear(String signedYear) {
		this.signedYear = signedYear;
	}

	public int getSmeOrgId() {
		return smeOrgId;
	}

	public void setSmeOrgId(int smeOrgId) {
		this.smeOrgId = smeOrgId;
	}

	public int getSmeCtpyOrgId() {
		return smeCtpyOrgId;
	}

	public void setSmeCtpyOrgId(int smeCtpyOrgId) {
		this.smeCtpyOrgId = smeCtpyOrgId;
	}

	public int getLenderOrgId() {
		return lenderOrgId;
	}

	public void setLenderOrgId(int lenderOrgId) {
		this.lenderOrgId = lenderOrgId;
	}

	public InputStream getLoanAgreementTemplate() {
		return loanAgreementTemplate;
	}

	

	public void setLoanAgreementTemplate(InputStream loanAgreementTemplate) {
		this.loanAgreementTemplate = loanAgreementTemplate;
	}

	public void setLoanAgreement(InputStream loanAgreement) {
		//System.arraycopy(loanAgreement, 0, this.loanAgreement = new byte [loanAgreement.length], 0, loanAgreement.length);
		this.loanAgreement = loanAgreement;
	}

	public InputStream getLoanAgreement() {
		return loanAgreement;
	}

	

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public Date getLoanDispatchDt() {
		return loanDispatchDt;
	}

	public void setLoanDispatchDt(Date loanDispatchDt) {
		this.loanDispatchDt = loanDispatchDt;
	}

	public Date getLoanCloseDt() {
		return loanCloseDt;
	}

	public void setLoanCloseDt(Date loanCloseDt) {
		this.loanCloseDt = loanCloseDt;
	}

	public String getLenderCompanyName() {
		return lenderCompanyName;
	}

	public void setLenderCompanyName(String lenderCompanyName) {
		this.lenderCompanyName = lenderCompanyName;
	}

	public String getAgreementRefId() {
		return agreementRefId;
	}

	public void setAgreementRefId(String agreementRefId) {
		this.agreementRefId = agreementRefId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLoanCloseDtDisplay() {
		return loanCloseDtDisplay;
	}

	public void setLoanCloseDtDisplay(String loanCloseDtDisplay) {
		this.loanCloseDtDisplay = loanCloseDtDisplay;
	}


		
}
