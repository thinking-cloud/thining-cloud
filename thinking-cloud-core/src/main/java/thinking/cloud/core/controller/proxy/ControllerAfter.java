package thinking.cloud.core.controller.proxy;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.aspectj.lang.JoinPoint;

/**
 * controller最终通知的接口
 * <P>
 * controller最终通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ControllerAfter {
	/**
	 * 处理最终通知的方法
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param point 连接点
	 */
	public void handler(ServletRequest request,ServletResponse response,JoinPoint point);
}
