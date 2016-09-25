package com.floatinvoice.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum EnquiryStatusEnum {

	NEW(0, "NEW"),
	CLOSED(1, "CLOSED"),
	QUALIFIED(2, "QUALIFIED"),
	STAGED(3, "STAGED"),
	RELEASED(4, "RELEASED"),
	REJECTED(5, "REJECTED");
	
	int code;
	String text;
	
	EnquiryStatusEnum(int code, String text){
		this.code = code;
		this.text = text;
	}
	
	private static final Map<Integer,EnquiryStatusEnum> lookup = new HashMap<>();

	static {
	     for(EnquiryStatusEnum s : EnumSet.allOf(EnquiryStatusEnum.class))
	          lookup.put(s.getCode(), s);
	}
	
	public static EnquiryStatusEnum get(int code) { 
	      return lookup.get(code); 
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
}
