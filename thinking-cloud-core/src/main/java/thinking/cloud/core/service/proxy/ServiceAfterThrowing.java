package thinking.cloud.core.service.proxy;

import org.aspectj.lang.JoinPoint;

/**
 * Service异常通知的接口
 * <P>
 * Service异常通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ServiceAfterThrowing {
	/**
	 * 处理异常通知的方法
	 * @param point 连接点
	 * @param throwable 异常对象
	 */
	public void handler(JoinPoint point,Throwable throwable) ;
}
