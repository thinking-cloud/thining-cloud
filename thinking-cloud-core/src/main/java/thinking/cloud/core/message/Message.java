/*
 * All rights Reserved, Designed by www.thinking-cloud.net
 * 文件名:Message.java
 * 作者：think
 * 创建日期:2018年10月25日 上午1:56:23
 */
package thinking.cloud.core.message;

import lombok.Data;

/**
 * 响应给调用者的消息格式
 * <p>响应给调用者的消息格式</p>
 *  
 * @author think
 * @param <T> 响应数据类型
 */
@Data
public class Message<T> {
	/** 响应码 */
	private String code;
	/** 响应消息 */
	private String msg;
	/** 响应时间戳 */
	private long timestamp = System.currentTimeMillis();
	/** 响应数据 */
	private T data;
	
	/**
	 * 构造函数
	 * @param code 响应码
	 * @param msg 响应消息
	 */
	public Message(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 构造函数
	 * @param code 响应码
	 * @param msg 响应消息
	 * @param data 响应数据
	 */
	public Message(String code, String msg, T data) {
		this(code,msg);
		this.data = data;
	}
	
	/**
	 * 构造函数
	 * @param code 响应码
	 * @param msg 响应消息
	 * @param timestamp 时间戳
	 */
	public Message(String code,String msg,Long timestamp) {
		this(code,msg);
		this.timestamp = timestamp;
	}
	
	/**
	 * 构造函数
	 * @param code 响应码
	 * @param msg 响应消息
	 * @param timestamp 时间戳
	 * @param data 响应数据
	 */
	public Message(String code,String msg,Long timestamp,T data) {
		this(code,msg,data);
		this.timestamp = timestamp;
	}
	
	/**
	 * 无参构造函数
	 */
	public Message() { }
	
	
}
