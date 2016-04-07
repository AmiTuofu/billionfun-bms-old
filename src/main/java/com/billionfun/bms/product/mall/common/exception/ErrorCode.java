package com.billionfun.bms.product.mall.common.exception;

public  enum ErrorCode {
	ERROR(999,"系统错误"),
	USER_EXIST_ERROR(901,"用户已存在");
	
	private int errCode;
	private String errMsg;
	private ErrorCode(int errCode,String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	/**
	 * 
	 * @Title: 得到错误编码 
	 * @Description: TODO
	 * @param @return 
	 * @return int
	 * @throws
	 */
	public int getErrCode() {
		return errCode;
	}
	
	/**
	 * 
	 * @Title: 得到错误信息 
	 * @Description: TODO
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public String getErrMsg() {
		return errMsg;
	}
}
