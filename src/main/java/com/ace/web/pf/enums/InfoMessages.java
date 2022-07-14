package com.ace.web.pf.enums;

public enum InfoMessages {
	Successfully_Save("G-I-001"), 
	Successfully_delete("G-I-002"), 
	Successfully_Send("G-I-003"),
	Successfully_Password_Update("G-I-004");

	private String code;

	InfoMessages(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}
}
