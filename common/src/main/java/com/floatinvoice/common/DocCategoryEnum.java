package com.floatinvoice.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DocCategoryEnum {


	IDPROOF(1, "IDPROOF"),
	ADDRESSPROOF(2, "ADDRESSPROOF" ),
	TAXRETURNS(3, "TAXRETURNS" ),
	OTHERS(4, "OTHERS"),
	INVOICETEMPLATE(5,"INVOICETEMPLATE");
	
	
	private static final Map<Integer,DocCategoryEnum> lookup = new HashMap<>();

	static {
	     for(DocCategoryEnum s : EnumSet.allOf(DocCategoryEnum.class))
	          lookup.put(s.getCode(), s);
	}
	
	public static DocCategoryEnum get(int code) { 
	      return lookup.get(code); 
	}
	
	int code;
	String name;
	
	DocCategoryEnum( int code,
	String name ){
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
