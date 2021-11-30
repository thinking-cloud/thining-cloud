package thinking.cloud.api.feign.interceptor;

import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thinking.cloud.beans.cache.ThreadLocalTables;

/**
 * 清理 
 * @author thinking
 *
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class ClearThreadLocalTables implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		ThreadLocalTables.clear();
		log.info("feign RequestInterceptor clear threadLocalTables success");
	}
}
