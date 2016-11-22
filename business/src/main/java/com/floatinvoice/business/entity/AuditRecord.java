package com.floatinvoice.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuditRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name="update_by")
	private String username;
	
	@Column(name="source_app")
	private int source;
	
	@Column(name="request_id")
	private String requestId;

	public String getUsername() {
		return username;
	}

	public int getSource() {
		return source;
	}

	public String getRequestId() {
		return requestId;
	}
	
	public AuditRecord(String userName, int source, String reqId){
		this.username = userName;
		this.source = source;
		this.requestId = reqId;
	}
	
	public AuditRecord(){}
}
