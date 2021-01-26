package thinking.cloud.api.controller.proxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;

/**
 * controller层前置通知的接口
 * <P>
 * controller层前置通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ControllerBefore {
	
	/**
	 * 前置通知的处理方法
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param point 连接点
	 */
	public void handler(HttpServletRequest request,HttpServletResponse response, JoinPoint point) ;
}
