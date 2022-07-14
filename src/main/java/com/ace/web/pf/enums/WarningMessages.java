package com.ace.web.pf.enums;

public enum WarningMessages {
	Confirm_Updated_data("G-W-001"),
	Cannot_Delete("G-W-002"),
	Already_exists("Tariff-W-001");
	
	private String code;

	WarningMessages(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}
}
