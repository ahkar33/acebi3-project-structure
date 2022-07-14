package com.ace.web.pf.enums;

public enum PageUrls {
	Login("pages/login"),
	Student("pages/generalSetting/student");

	private String value;

	PageUrls(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
