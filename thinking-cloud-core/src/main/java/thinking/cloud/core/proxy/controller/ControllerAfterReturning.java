package thinking.cloud.api.controller.proxy;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.aspectj.lang.JoinPoint;

/**
 * controller后置通知的接口
 * <P>
 * controller后置通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */

public interface ControllerAfterReturning {
	
	/**
	 * 处理后置通知的方法
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param point 连接点
	 * @param returnObj 返回值
	 */
	public void handler(ServletRequest request,ServletResponse response, JoinPoint point,Object returnObj);
		
}
