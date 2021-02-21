package thinking.cloud.core.proxy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;

import io.swagger.models.Model;
import thinking.cloud.core.proxy.ProxyHandler;

/**
 * controller层前置通知的接口
 * <P>
 * controller层前置通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ControllerBefore extends ProxyHandler{
	public void handler(Model model);
}
