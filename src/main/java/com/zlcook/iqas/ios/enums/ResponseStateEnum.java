package com.zlcook.iqas.ios.enums;
/**
* @author 周亮 
* @version 创建时间：2017年2月28日 下午8:35:53
* 请求响应状态 
*/
public enum ResponseStateEnum {

	
	/**
	 * 失败
	 */
	FAILURE(0,"failure"),	
	/**
	 * 成功
	 */
	SUCCESS(1,"success"),

	/**
	 * 常见的请求异常
	 */
	BAD_REQUEST(400,"请求参数不对"),

	NOT_FOUND(404,"请求方法不存在"),
	
	METHOD_NOT_ALLOWED(405,"不支持请求当前方法"),
	
	UNSUPPORTED_MEDIA_TYPE(415,"不支持当前媒体类型"),
	
	NOT_ACCEPTABLE(406,"不接收当前媒体类型"),
	
	INTERNAL_SERVER_ERROR(500,"服务器异常"),
	
	UNKNOWN_SERVER_ERROR(1000,"未知异常"),
	
	DATAINTEGRITY_VIOLATION_EXCEPTION(1001,"操作数据库出现异常"),
	
	/**
	 * 自定义异常
	 */
	
	PARAM_ERROR(1002,"参数有误!"),
	
	USER_EXIST(2000,"用户已存在"),
	
	USER_LOGING_PARROR_ERROR(2001,"输出参数有误失败"),
	USER_LOGING_ERROR(2002,"用户登录失败");
	
	private int status;
	private String message;
	private ResponseStateEnum(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	
	
}
