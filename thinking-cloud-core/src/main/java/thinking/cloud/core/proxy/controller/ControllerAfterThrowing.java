package thinking.cloud.core.proxy.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.aspectj.lang.JoinPoint;

import io.swagger.models.Model;
import thinking.cloud.core.proxy.ProxyHandler;

/**
 * controller异常通知的接口
 * <P>
 * 标识为controller异常通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ControllerAfterThrowing extends ProxyHandler{
	public void handler(Model model);
}
