package thinking.cloud.api.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import thinking.cloud.api.constant.ThreadLocalTables;

/**
 * 清理 
 * @author thinking
 *
 */
@Slf4j
public class ClearThreadLocalTables implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		ThreadLocalTables.clear();
		log.info("feign RequestInterceptor clear threadLocalTables success");
	}
}
