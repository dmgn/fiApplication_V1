package com.floatinvoice.messages;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tradeLicense")
@XmlAccessorType(value=XmlAccessType.PROPERTY)
public class TradeLicense extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String licenseNo;
	private String licensingAuthority;
	private Date incorporationDt;
	private Date licenseExpiryDt;
	private String tradeActivities;
	
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicensingAuthority() {
		return licensingAuthority;
	}
	public void setLicensingAuthority(String licensingAuthority) {
		this.licensingAuthority = licensingAuthority;
	}
	public Date getIncorporationDt() {
		return incorporationDt;
	}
	public void setIncorporationDt(Date incorporationDt) {
		this.incorporationDt = incorporationDt;
	}
	public Date getLicenseExpiryDt() {
		return licenseExpiryDt;
	}
	public void setLicenseExpiryDt(Date licenseExpiryDt) {
		this.licenseExpiryDt = licenseExpiryDt;
	}
	public String getTradeActivities() {
		return tradeActivities;
	}
	public void setTradeActivities(String tradeActivities) {
		this.tradeActivities = tradeActivities;
	}
	
	

}
