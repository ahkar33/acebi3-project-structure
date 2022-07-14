package com.ace.web.pf.enums;

public enum ContainerTypeSize {

	T20GP("22: 20' 8' 6\""),
	T40GP("42: 40' 8' 6\""),
	T40HQ("45: 40' 9' 6\""),
	T40HC("45: 40' 9' 6\""),
	S20TK("20TK"),
	S40FL("40FL");

	private String code;

	ContainerTypeSize(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}
}
