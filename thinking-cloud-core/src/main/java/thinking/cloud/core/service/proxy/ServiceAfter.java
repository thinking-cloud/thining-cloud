package thinking.cloud.core.service.proxy;

import org.aspectj.lang.JoinPoint;

/**
 * Service最终通知的接口
 * <P>
 * Service最终通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ServiceAfter {
	/**
	 * 处理最终通知的方法
	 * @param point 连接点
	 */
	public void handler(JoinPoint point);
}
