/*
 * All rights Reserved, Designed by www.thinking-cloud.net
 * 文件名:Message.java
 * 作者：think
 * 创建日期:2018年10月25日 上午1:56:23
 */
package thinking.cloud.api.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import thinking.cloud.api.constant.MESSAGE;

/**
 * 响应给调用者的消息格式
 * <p>响应给调用者的消息格式</p>
 *  
 * @author think
 * @param <T> 响应数据类型
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {
	
	/** 时间戳 */
	private Long timestamp=System.currentTimeMillis();
	/** 响应码 */
	private String code;
	/** 响应消息 */
	private String msg;
	/** 响应数据 */
	private T data;
	
	/**
	 * 构建success的响应结果
	 * @param data 响应数据
	 * @return
	 */
	public static <T> Message success(T data){
		return Message
				.builder()
				.code(MESSAGE.SUCCESS.code())
				.msg(MESSAGE.SUCCESS.name())
				.data(data)
				.build();
	}

	/**
	 * 构建success的响应结果
	 * @return
	 */
	public static Message success(){
		return Message
				.builder()
				.code(MESSAGE.SUCCESS.code())
				.msg("操作成功")
				.build();
	}

	/**
	 * 构建后台程序异常的响应结果
	 * @return
	 */
	public static <T> Message error(){
		return Message
				.builder()
				.code(MESSAGE.ERROR.code())
				.msg("后台出现异常")
				.build();
	}

	/**
	 * 构建后台程序异常的响应结果
	 * @return
	 */
	public static  Message error(Throwable e){
		return Message
				.builder()
				.code(MESSAGE.ERROR.code())
				.msg("后台出现异常")
				.data(e.getMessage())
				.build();
	}

	/**
	 * 构建验证失败的消息
	 * @param message 验证消息
	 * @return
	 */
	public static Message failure(String message){
		return Message
				.builder()
				.code(MESSAGE.FAILURE.code())
				.msg(message)
				.build();
	}

	/**
	 * 构建自定义的message响应消息
	 * @param code 响应码
	 * @param message 响应消息
	 * @param data 响应数据
	 * @param <T> 响应数据类型
	 * @return
	 */
	public static <T> Message message(String code,String message, T data){
		return Message
				.builder()
				.code(code)
				.msg(message)
				.data(data)
				.build();
	}
}
