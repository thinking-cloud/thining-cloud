package thinking.cloud.core.filter;

import java.io.IOException;
import java.util.UUID;

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

/**
 * 
 * 添加x-request-id请求头的过滤器
 * @author zhouxinke
 * @date 2021年2月24日
 */
@Configuration
@ServletComponentScan("thinking.cloud")
@WebFilter(urlPatterns = "/*")
@Slf4j
public class Filter100_XRequestFilter extends ThinkingCloudBaseFilter{

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String url = httpServletRequest.getRequestURL().toString();
		if(url.indexOf("/webjars/springfox-swagger-ui/favicon-32x32.png")>0) {
			System.out.println(url);
		}
		if (isExcludedUrls(servletRequest)){
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
				log.info("request-url:{} get x-request-id:{} ", url,xRequestId);
			}
			// 执行
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			httpServletResponse.setHeader("x-request-id", xRequestId);
		}
	}
}
