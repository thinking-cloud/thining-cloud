package thinking.cloud.core.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

/**
 * 所有过滤器的顶级抽象类, 封装公共处理
 * @author thinking
 *
 */
@Data
public abstract class ThinkingCloudBaseFilter implements Filter {
	
	private static final String ATTRIVBUTE_KEY_EXCLUDED_URL_FLAG= "IS_EXCLUDED_URL";
	
	@Value("${thinking.cloud.filter.excluded.url:''}")
	private String[] excludedUrls;
	
	private String[] getExcludedUrls() {
		List<String> urls = new ArrayList<>();
		urls.add("*/actuator/health");
		urls.add("*/springfox-swagger-ui*");
		urls.add("*/swagger*");
		urls.add("*/v2/api-docs*");
		if(excludedUrls!=null || excludedUrls.length>1  || !excludedUrls[0].equals("")) {
			Collections.addAll(urls, excludedUrls);
		}
		return urls.toArray(new String[urls.size()]);
	}
	
	/**
	 * 判断当前url是否为排除的url
	 * @return true:是排除的url  false:不是排除的,需要进行处理的
	 */
	public boolean isExcludedUrls(ServletRequest servletRequest) {
		Object isExcludedUrl = servletRequest.getAttribute(ATTRIVBUTE_KEY_EXCLUDED_URL_FLAG);
		HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		if(isExcludedUrl==null) {
			String url = httpServletRequest.getRequestURL().toString();
			isExcludedUrl = false;
			for (String u : getExcludedUrls()) {
				if(u.startsWith("*") && u.endsWith("*")) {	// 前后都有*
					u = u.substring(1,u.length()-1);
					if(url.indexOf(u)>0) {
						isExcludedUrl = true;
						break;
					}
				}else if(u.startsWith("*")) { // *开头
					u = u.substring(1,u.length());
					if(url.endsWith(u)) {
						isExcludedUrl = true;
						break;
					}
				}else if(u.endsWith("*")) {	// *结尾
					u = u.substring(0, u.length()-1);
					if(url.startsWith(u)) {
						isExcludedUrl = true;
						break;
					}
				}
			}
			servletRequest.setAttribute(ATTRIVBUTE_KEY_EXCLUDED_URL_FLAG, isExcludedUrl);
		}
		
		return (Boolean)isExcludedUrl;
	}

}
