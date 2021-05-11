 package thinking.cloud.proxy.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import thinking.cloud.core.filter.ThinkingCloudBaseFilter;
import thinking.cloud.proxy.model.Model;

/**
 * 初始化 代理Mode的过滤器
 * 
 * @author thinking
 */

@Configuration
@ServletComponentScan("thinking.cloud")
@WebFilter(urlPatterns = "/*")
@Slf4j
public class Filter200_ProxyModelFilter extends ThinkingCloudBaseFilter {

	@Override
	protected void invork(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		String url = httpServletRequest.getRequestURL().toString();
		// 获取设置 xrequestid
		String xRequestId = httpServletRequest.getHeader("x-request-id");
		log.info("request-url:{}  x-request-id:{} ", url, xRequestId);

		// 生成model
		Model model = Model.builder().xRequestId(xRequestId).request(httpServletRequest)
				.response(httpServletResponse).timestamp(System.currentTimeMillis()).build();
		// 设置model
		Model.setModel(model);
		// 执行
		filterChain.doFilter(httpServletRequest, httpServletResponse);
		// 清理model
		Model.clear();

	}

	@Override
	protected String filterName() {
		return "初始化代理中,数据传递的Model对象";
	}
}
