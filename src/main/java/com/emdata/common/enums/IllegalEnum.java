package com.emdata.common.enums;

public enum IllegalEnum {

	ILLEGAL(1, "违法"),
	ILLEGAL_NOT(2, "未违法"),
	SUSPECTED(0, "疑似");

	private int code;

	private String name;

	private IllegalEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}


	public int getCode() {
		return code;
	}


	public static String getName(Integer value) {
		for (IllegalEnum c : IllegalEnum.values()) {
			if (c.code == value) {
				return c.name;
			}
		}
		return null;
	}

	public String getName() {

		return name;
	}
}
