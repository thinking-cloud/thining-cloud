package thinking.cloud.core.service.proxy;

import org.aspectj.lang.JoinPoint;

/**
 * service后置通知的接口
 * <P>
 * service后置通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */

public interface ServiceAfterReturning {
	
	/**
	 * 处理后置通知的方法
	 * @param point 连接点
	 * @param returnObj 返回值
	 */
	public void handler(JoinPoint point,Object returnObj);
		
}
