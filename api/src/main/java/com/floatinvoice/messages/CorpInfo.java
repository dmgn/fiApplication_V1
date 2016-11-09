package com.floatinvoice.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="corpInfo")
@XmlAccessorType(value=XmlAccessType.PROPERTY)
public class CorpInfo extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String legalStatus;
	private String shareCapital;
	private String shareHoldingStruct;
	private String dirList;
	private String keyBankers;
	private String statutoryAuditors;
	private int employeeStrength;
	private String branchesWarehouses;
	private String parentAffiliatedComps;
	public String getLegalStatus() {
		return legalStatus;
	}
	public void setLegalStatus(String legalStatus) {
		this.legalStatus = legalStatus;
	}
	public String getShareCapital() {
		return shareCapital;
	}
	public void setShareCapital(String shareCapital) {
		this.shareCapital = shareCapital;
	}
	public String getShareHoldingStruct() {
		return shareHoldingStruct;
	}
	public void setShareHoldingStruct(String shareHoldingStruct) {
		this.shareHoldingStruct = shareHoldingStruct;
	}
	public String getDirList() {
		return dirList;
	}
	public void setDirList(String dirList) {
		this.dirList = dirList;
	}
	public String getKeyBankers() {
		return keyBankers;
	}
	public void setKeyBankers(String keyBankers) {
		this.keyBankers = keyBankers;
	}
	public String getStatutoryAuditors() {
		return statutoryAuditors;
	}
	public void setStatutoryAuditors(String statutoryAuditors) {
		this.statutoryAuditors = statutoryAuditors;
	}
	public int getEmployeeStrength() {
		return employeeStrength;
	}
	public void setEmployeeStrength(int employeeStrength) {
		this.employeeStrength = employeeStrength;
	}
	public String getBranchesWarehouses() {
		return branchesWarehouses;
	}
	public void setBranchesWarehouses(String branchesWarehouses) {
		this.branchesWarehouses = branchesWarehouses;
	}
	public String getParentAffiliatedComps() {
		return parentAffiliatedComps;
	}
	public void setParentAffiliatedComps(String parentAffiliatedComps) {
		this.parentAffiliatedComps = parentAffiliatedComps;
	}
}
