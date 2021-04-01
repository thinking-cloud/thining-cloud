package thinking.cloud.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import thinking.cloud.core.proxy.model.Model;

/**
 * 初始化 代理Mode的过滤器
 * @author thinking
 */

@Configuration
@ServletComponentScan("thinking.cloud")
@WebFilter(urlPatterns = "/*")
@Slf4j
public class Filter200_ProxyModelFilter extends ThinkingCloudBaseFilter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String url = httpServletRequest.getRequestURL().toString();
		if (isExcludedUrls(servletRequest)){
			chain.doFilter(httpServletRequest, httpServletResponse);
		}else {
			// 获取设置 xrequestid
			String xRequestId = httpServletRequest.getHeader("x-request-id");
			log.info("request-url:{}  x-request-id:{} ", url,xRequestId);

			// 生成model
			Model<Object> model = Model.builder().xRequestId(xRequestId).request(httpServletRequest).response(httpServletResponse).timestamp(System.currentTimeMillis()).build();
			// 设置model
			Model.setModel(model);
			// 执行
			chain.doFilter(httpServletRequest, httpServletResponse);
			// 清理model
			Model.clear();
		}
	}
	
}
