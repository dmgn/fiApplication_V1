package com.floatinvoice.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum LoanStatus {

	DEFAULT(-1, "DEFAULT"),
	SANCTIONED(0, "SANCTIONED"),
	ACTIVE(1, "ACTIVE"),
	PAID(2, "PAID");
	
	int code;
	String text;
	
	LoanStatus(int code, String text){
		this.code = code;
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	private static final Map<Integer,LoanStatus> lookup = new HashMap<>();

	static {
	     for(LoanStatus s : EnumSet.allOf(LoanStatus.class))
	          lookup.put(s.getCode(), s);
	}
	
	public static LoanStatus get(int code) { 
	      return lookup.get(code); 
	}
	
	

}
