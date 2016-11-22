package com.floatinvoice.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import com.floatinvoice.common.UserContext;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Embedded
	private AuditRecord auditRecord;
	
	@Column(name="update_date", insertable=true, updatable=true)
	private Date lastUpdated = new Date();
	
	public abstract Long getId();
	
	public void auditChange() {
		this.auditRecord = new AuditRecord(UserContext.getUserName(), UserContext.getSourceApp(), UUID.randomUUID().toString());
		this.lastUpdated = new Date();
	}
}