package thinking.cloud.core.filter;

import java.io.IOException;

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

import thinking.cloud.core.controller.proxy.ControllerProxy;

/**
 * 
 * <P>
 * </P>
 * @author zhouxinke
 * @date 2020年12月2日
 */
@Configuration
@ServletComponentScan("thinking.cloud")
@WebFilter(urlPatterns = "/*", filterName = "controllerProxyFilter")
public class ControllerProxyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	ControllerProxy.httpRequest.set((HttpServletRequest)servletRequest);
    	ControllerProxy.httpResponse.set((HttpServletResponse)servletResponse);
    	filterChain.doFilter(servletRequest, servletResponse);
    }
 
    @Override
    public void destroy() {
 
    }
}
