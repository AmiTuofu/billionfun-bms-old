package com.billionfun.bms.product.mall.common.status;

public enum Status {
	NEW("N","新建"),
	DELETE("D","无效");
	private String statusCode;
	private String statusMsg;
	private Status(String statusCode,String statusMsg) {
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
}
