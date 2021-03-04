package thinking.cloud.core.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import thinking.cloud.api.entity.Model;
import thinking.cloud.core.proxy.ProxyHandler;
import thinking.cloud.core.proxy.ThinkingProxy;
import thinking.cloud.core.proxy.controller.ControllerProxy;

/**
 * 
 * @author zhouxinke
 * @date 2021年2月24日
 */
@Configuration
@ServletComponentScan("thinking.cloud")
@WebFilter(urlPatterns = "/*", filterName = "ThinkingFilter")
@Slf4j
public class ThinkingFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String url = httpServletRequest.getRequestURL().toString();
		if (url.indexOf("/actuator/health") >= 0){
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}else {
			// 获取设置 xrequestid
			String xRequestId = httpServletRequest.getHeader("x-request-id");

			if (xRequestId == null || "".equals(xRequestId.trim())) {
				xRequestId = UUID.randomUUID().toString();
				ThinkingServletRequestWarpper tsrw = new ThinkingServletRequestWarpper(httpServletRequest);
				tsrw.addHeader("x-request-id", xRequestId);
				httpServletRequest = tsrw;
				log.info("request-url:{} generator x-request-id:{}",url, xRequestId);
			}else {
				log.info("request-url:{}  x-request-id:{} ", url,xRequestId);
			}

			// 生成model
			Model<Object> model = Model.builder().xRequestId(xRequestId).request(httpServletRequest).response(httpServletResponse).timestamp(System.currentTimeMillis()).build();
			Model.setModel(model);
			// 执行
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			// 清理model
			Model.clean();

		}
	}

	@Override
	public void destroy() {

	}
}
