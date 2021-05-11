package thinking.cloud.web.configure;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.SerializationFeature;

import thinking.cloud.web.converts.HttpMessageNullValueConvert;

/**
 * 配置HttpMessageConvert的配置类
 * 1. 添加空值处理
 * @author thinking
 */
@Configuration
public class HttpMessageConvertConfiguration implements WebMvcConfigurer {


	public HttpMessageConverter<?> fastConverter() {
		MappingJackson2HttpMessageConverter fastConverter = new MappingJackson2HttpMessageConverter();
		fastConverter.getObjectMapper().getSerializerProvider().setNullValueSerializer(new HttpMessageNullValueConvert());
		fastConverter.getObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return fastConverter;
	}
	
	public HttpMessageConverter<?> OtherNUllConverter(){
		return null;
	} 

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.clear();
		converters.add(fastConverter());
	}
}
