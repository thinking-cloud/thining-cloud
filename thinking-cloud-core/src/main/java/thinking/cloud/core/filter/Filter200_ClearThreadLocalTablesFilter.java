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
import thinking.cloud.api.constant.ThreadLocalTables;


@Configuration
@ServletComponentScan("thinking.cloud")
@WebFilter(urlPatterns = "/*")
@Slf4j
public class Filter200_ClearThreadLocalTablesFilter extends ThinkingCloudBaseFilter {
	
	
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
			// 执行
			chain.doFilter(httpServletRequest, httpServletResponse);
			// 清理
			ThreadLocalTables.clear();
			log.info("request-url:{}  x-request-id:{} clear thread local", url,xRequestId);
		}

	}

}
