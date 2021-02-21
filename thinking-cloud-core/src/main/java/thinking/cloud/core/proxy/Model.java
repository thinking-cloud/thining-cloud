package thinking.cloud.core.proxy;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.aspectj.lang.JoinPoint;

import lombok.Builder;
import lombok.Data;

/**
 * model类
 * @author think
 */
@Data
@Builder
public class Model {	
	private ServletRequest request;
	private ServletResponse response;
	private JoinPoint point;
	private Object returnObj;
	private Throwable throwable;
}
