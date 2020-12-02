package thinking.cloud.rest.client.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import thinking.cloud.rest.client.entity.RespData;
import thinking.cloud.rest.client.exception.AuthorizationIsNullException;

/**
 * <P>
 * 	通过RestTemplate发送请求的工具类
 * </P>
 * @author think
 * @date 2020年11月18日
 */
@Component
@Slf4j
public class RestUtils {
	@Autowired
	private RestTemplate restTemplate;
	
	
	/**
	 * 打印异常日志信息
	 * @param status 请求类型
	 * @param url 请求url 
	 * @param respData 响应信息
	 */
	public void loggerRequestInfo(HttpMethod method, String url) {
		log.info("请求: {} {}",method.name(), url);
	}
	
	/**
	 * 打印异常日志信息
	 * @param status 请求类型
	 * @param url 请求url 
	 * @param respData 响应信息
	 */
	public void loggerResponseInfo(RespData<?> respData) {
		String body = respData.getData() ==null?respData.getError():respData.getData().toString();
		log.info("响应: {} {}",respData.getStatus(),body);
	}

	
	/**
	 * 成功响应转为响应对象
	 * @param <R> 响应数据对象
	 * @param responseEntity 响应对象
	 * @param bodyClass 响应数据对象字节码
	 * @return 响应对象
	 */
	public <R> RespData<R> successResp2RespData(ResponseEntity<R> responseEntity,Class<R> bodyClass) {
		RespData<R> respData = new RespData<>();
		HttpStatus statusCode = responseEntity.getStatusCode();
		R body = responseEntity.getBody();
		respData.setStatus(statusCode);
		respData.setData(body);
		return respData;
	}
	
	/**
	 * 请求失败转为响应对象
	 * @param e 异常信息
	 * @return 响应对象
	 */
	public <R> RespData<R> errorResp2RespData(HttpClientErrorException e){
		RespData<R> respData = new RespData<>();
		HttpStatus statusCode = e.getStatusCode();
		String body = e.getResponseBodyAsString();
		respData.setStatus(statusCode);
		respData.setError(body);
		return respData;
	}
	
	/**
	 * 对rest进行简单封装
	 * @param <R> 响应数据对象
	 * @param url 请求url
	 * @param method 请求方式
	 * @param requestEntity 请求体
	 * @param responseType 响应数据对象字节码
	 * @return
	 */
	public <R> RespData<R> rest(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<R> responseType){
		RespData<R> respData = null;
		try {			
			loggerRequestInfo(method,url);
			ResponseEntity<R> responseEntity = restTemplate.exchange(url, method, requestEntity, responseType);
			respData = successResp2RespData(responseEntity, responseType);
		}catch (HttpClientErrorException e) {
			respData = errorResp2RespData(e);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		loggerResponseInfo(respData);
		return respData;
	}
	
	/**
	 * 生成默认的请求头
	 * @param authorization 认证信息
	 * @return
	 */
	public HttpHeaders generaHeader(String authorization) {
		if(StringUtils.isEmpty(authorization)) {
			throw new AuthorizationIsNullException();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, authorization);
		return headers;
	}
}
