package com.ace.web.pf.enums;

public enum ErrorMessage {
	Unexpected_Error("G-E-001"),
	Duplicate_Error("G-E-005"),
	Notify_Party_Name_Required("Manifest-E-009");

	private String code;

	ErrorMessage(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}

}
