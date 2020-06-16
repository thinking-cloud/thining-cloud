package thinking.cloud.core.handler;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import thinking.cloud.core.enums.MESSAGE;
import thinking.cloud.core.message.Message;
import thinking.cloud.validation.exception.ValidationFailedException;

/**
 * 过滤异常响应，转为标准json
 * @author think
 */
@ControllerAdvice
public class MvcExceptionHandler {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(MvcExceptionHandler.class);
	/**
	 * 异常处理
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Message error(Throwable throwable){
		logger.error("thinking-core拦截异常：",throwable);
		return new Message<>(MESSAGE.ERROR.code(),MESSAGE.ERROR.name(),throwable.getMessage());
	}
	
	/**
	 * 验证失败异常处理
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ValidationFailedException.class)
	@ResponseBody
	public Message failed(ValidationFailedException exception){
		logger.error("thinking-core拦截异常：",exception);
		return new Message<>(MESSAGE.FAILURE.code(),MESSAGE.FAILURE.name(),exception.getMessage());
	}
}