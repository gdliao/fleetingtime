package com.fleetingtime.exception;

/**
 * Title: 业务异常类
 * Description: 当系统中需要抛出一个业务异常时，创建此异常。
 * 				此异常中包含retCode,retMsg属性，分别代表业务异常的返回代码和返回信息，可以做为错误判断依据。
 * @author liaogd
 */
public class BusinessException extends RuntimeException {

	/**
	 *返回代码
	 */
	private String retCode;
	
	/**
	 *返回信息
	 */
	private String retMsg;
	
	public String getRetCode() {
		return retCode;
	}
	

	public String getRetMsg() {
		return retMsg;
	}

	
	
	public BusinessException(String retCode,String message) {
		this(message);
		this.retCode = retCode;
		this.retMsg = message;
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
