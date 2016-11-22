package com.floatinvoice.messages;

public class BuyerDtlsMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String compName;
	
	private String website;
	
	private String city;
	
	private String country;
	
	private String phone;
	
	private String fax;
	
	private String regName;
	
	private String email;
	
	private String contactPerson;
	
	private String reqLimit;

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getReqLimit() {
		return reqLimit;
	}

	public void setReqLimit(String reqLimit) {
		this.reqLimit = reqLimit;
	}
	
	
	
}
