package thinking.cloud.core.service.proxy;

import org.aspectj.lang.JoinPoint;

/**
 * service层前置通知的接口
 * <P>
 * service层前置通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ServiceBefore {
	
	/**
	 * 前置通知的处理方法
	 * @param point 连接点
	 */
	public void handler(JoinPoint point) ;
}
