package thinking.cloud.rest.client;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import thinking.cloud.rest.client.entity.RespData;
import thinking.cloud.rest.client.entity.RestDownloadFile;
import thinking.cloud.rest.client.entity.RestUploadFile;
import thinking.cloud.rest.client.utils.RestUrlPramsUtils;
import thinking.cloud.rest.client.utils.RestUtils;
import thinking.cloud.utils.data.GsonUtils;

/**
 * <P>
 * 	发送rest请求的工具类
 * </P>
 * 
 * @author think
 * @date 2020年11月18日
 */
@Component
@Data
public class RestClient {
	@Autowired
	private RestUtils restUtils;
	
	/**
	 * 返回加载的restTemplate
	 * @return
	 */
	public RestTemplate restTemplate() {
		return restUtils.restTemplate();
	}

	/**
	 * 发送get请求
	 * 
	 * @param <P>       请求参数对象
	 * @param <R>       响应数据对象
	 * @param url       请求url
	 * @param headers   请求头
	 * @param params    请求参数
	 * @param bodyClass 响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名	 
	 * @return
	 */
	public <P, R> RespData<R> get(String url, HttpHeaders headers, P params, Class<R> bodyClass,String... exculdeFieldNames) {
		url = RestUrlPramsUtils.replaceVariablePath(url,params);
		url = RestUrlPramsUtils.obj2UrlParams(url, params, true,exculdeFieldNames);
		return restUtils.rest(url, HttpMethod.GET, new HttpEntity<String>(headers), bodyClass);

	}

	/**
	 * 发送get请求
	 * 
	 * @param <P>           请求参数对象
	 * @param <R>           响应数据对象
	 * @param url           请求url
	 * @param authorization 认证请求头
	 * @param params        请求参数
	 * @param bodyClass     响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <P, R> RespData<R> get(String url, String authorization, P params, Class<R> bodyClass,String... exculdeFieldNames) {
		return get(url, restUtils.generaHeader(authorization), params, bodyClass,exculdeFieldNames);
	}

	/**
	 * 发送post请求
	 * 
	 * @param <P>       请求参数对象
	 * @param <R>       响应数据对象
	 * @param url       请求url
	 * @param headers   请求头
	 * @param params    请求参数
	 * @param bodyClass 响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <P, R> RespData<R> post(String url, HttpHeaders headers, P params, Class<R> bodyClass,String... exculdeFieldNames) {
		url = RestUrlPramsUtils.replaceVariablePath(url,params);
		String paramsJson = GsonUtils.toJson(params);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return restUtils.rest(url, HttpMethod.POST, new HttpEntity<String>(paramsJson, headers), bodyClass);
	}

	/**
	 * 发送post请求
	 * 
	 * @param <P>           请求参数对象
	 * @param <R>           响应数据对象
	 * @param url           请求url
	 * @param authorization 认证信息
	 * @param params        请求参数
	 * @param bodyClass     响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <P, R> RespData<R> post(String url, String authorization, P params, Class<R> bodyClass,String... exculdeFieldNames) {
		return post(url, restUtils.generaHeader(authorization), params, bodyClass,exculdeFieldNames);
	}

	/**
	 * 发送put请求
	 * 
	 * @param <P>       请求参数对象
	 * @param <R>       响应数据对象
	 * @param url       请求url
	 * @param headers   请求头
	 * @param params    请求参数
	 * @param bodyClass 响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <P, R> RespData<R> put(String url, HttpHeaders headers, P params, Class<R> bodyClass,String... exculdeFieldNames) {
		url = RestUrlPramsUtils.replaceVariablePath(url,params);
		String paramsJson = GsonUtils.toJson(params);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return restUtils.rest(url, HttpMethod.PUT, new HttpEntity<String>(paramsJson, headers), bodyClass);
	}

	/**
	 * 发送put请求
	 * 
	 * @param <P>           请求参数对象
	 * @param <R>           响应数据对象
	 * @param url           请求url
	 * @param authorization 认证信息
	 * @param params        请求参数
	 * @param bodyClass     响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <P, R> RespData<R> put(String url, String authorization, P params, Class<R> bodyClass,String... exculdeFieldNames) {
		return put(url, restUtils.generaHeader(authorization), params, bodyClass,exculdeFieldNames);
	}

	/**
	 * 发送delete请求
	 * 
	 * @param <P>       请求参数对象
	 * @param <R>       响应数据对象
	 * @param url       请求url
	 * @param headers   请求头
	 * @param params    请求参数
	 * @param bodyClass 响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <P, R> RespData<R> delete(String url, HttpHeaders headers, P params, Class<R> bodyClass,String... exculdeFieldNames) {
		url = RestUrlPramsUtils.replaceVariablePath(url,params);
		String paramsJson = GsonUtils.toJson(params);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return restUtils.rest(url, HttpMethod.DELETE, new HttpEntity<String>(paramsJson, headers), bodyClass);
	}

	/**
	 * 发送delete请求
	 * 
	 * @param <P>           请求参数对象
	 * @param <R>           响应数据对象
	 * @param url           请求url
	 * @param authorization 认证信息
	 * @param params        请求参数
	 * @param bodyClass     响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <P, R> RespData<R> delete(String url, String authorization, P params, Class<R> bodyClass,String... exculdeFieldNames) {
		return delete(url, restUtils.generaHeader(authorization), params, bodyClass,exculdeFieldNames);
	}

	/**
	 * 
	 * @param <P>	请求参数对象
	 * @param <R>	响应数据对象
	 * @param url 	请求url
	 * @param method    请求方式
	 * @param headers 请求头
	 * @param params 请求参数对象
	 * @param bodyClass 响应对象字节码
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <R> RespData<R> upload(String url, HttpMethod method,HttpHeaders headers, RestUploadFile file, Class<R> bodyClass,String... exculdeFieldNames) {
		url = RestUrlPramsUtils.replaceVariablePath(url,file);
		if(headers ==null) {
			headers = new HttpHeaders();
		}
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> paramsMap = RestUrlPramsUtils.obj2MultiValueMap(file, true);
		return restUtils.rest(url, method, new HttpEntity<MultiValueMap<String, Object>>(paramsMap, headers),
				bodyClass);
	}

	/**
	 * 上传文件
	 * 
	 * @param <P>       请求参数对象
	 * @param <R>       响应数据对象
	 * @param url       请求url
	 * @param method    请求方式
	 * @param headers   请求头
	 * @param params    请求参数
	 * @param bodyClass 响应数据对象
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public <R> RespData<R> upload(String url, HttpMethod method,String authorization, RestUploadFile file, Class<R> bodyClass,String... exculdeFieldNames) {
		HttpHeaders headers = restUtils.generaHeader(authorization);
		return upload(url, method,headers, file, bodyClass,exculdeFieldNames);
	}

	/**
	 * 下载小文件
	 * <p>
	 * 	文件数据存储在内存中
	 * </p>
	 * @param url       请求url
	 * @param headers   请求头
	 * @param restFile  请求数据
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public RespData<Resource> download(String url, HttpHeaders headers, RestDownloadFile file,String... exculdeFieldNames) {
		url = RestUrlPramsUtils.replaceVariablePath(url,file);
		url = RestUrlPramsUtils.obj2UrlParams(url, file, true, exculdeFieldNames);
		return restUtils.rest(url, HttpMethod.GET, new HttpEntity<String>(headers), Resource.class);
	}
	
	/**
	 * 下载小文件
	 * <p>
	 * 	文件数据存储在内存中
	 * </p>
	 * @param url 请求url
	 * @param authorization 认证信息 
	 * @param restFile 请求数据
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public RespData<Resource> download(String url, String authorization, RestDownloadFile file,String... exculdeFieldNames) {
		HttpHeaders headers = restUtils.generaHeader(authorization);
		return download(url, headers, file,exculdeFieldNames);
	}

	/**
	 * 下载大文件
	 * <p>
	 * 	文件数据存储到硬盘
	 * </p>
	 * @param url 下载地址
	 * @param headers 请求头
	 * @param restFile 请求信息 
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public RespData<File> downloadBigFile(String url, HttpHeaders headers, RestDownloadFile fileInfo,String... exculdeFieldNames) {
		RespData<File> respData = new RespData<>();
		url = RestUrlPramsUtils.replaceVariablePath(url,respData);
		url = RestUrlPramsUtils.obj2UrlParams(url, fileInfo, true);
		try {
			restUtils.loggerRequestInfo(HttpMethod.GET, url);
			File file = restTemplate().execute(url, HttpMethod.GET,request-> {
				for (Entry<String, List<String>> header : headers.entrySet()) {
					request.getHeaders().addAll(header.getKey(), header.getValue());
				}			
			}, clientHttpResponse -> {
				File ret = File.createTempFile(fileInfo.getFilePath(), UUID.randomUUID()+"-"+fileInfo.getFileName());
				StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
				return ret;
			});
			HttpStatus statusCode = HttpStatus.OK;
			respData.setStatus(statusCode);
			respData.setData(file);
			restUtils.loggerResponseInfo(respData);
			return respData;
		}catch (HttpClientErrorException e) {
			return restUtils.errorResp2RespData(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 下载大文件
	 * <p>
	 * 	文件数据存储到硬盘
	 * </p>
	 * @param url 下载地址 
	 * @param authorization 认证信息
	 * @param restFile 请求信息
	 * @param exculdeFieldNames 过滤掉的属性名
	 * @return
	 */
	public RespData<Resource> downloadBigFile(String url, String authorization, RestDownloadFile restFile,String... exculdeFieldNames) {
		HttpHeaders generaHeader = restUtils.generaHeader(authorization);
		return download(url, generaHeader, restFile,exculdeFieldNames);
	}
	
	/**
	 * 获取响应头
	 * @param url 请求url
	 * @param method 请求方法
	 * @param paramObj 请求参数 
	 * @return 响应头
	 */
	public RespData<HttpHeaders> responseHeaders(String url, HttpMethod method,Object paramObj) {
		RespData<HttpHeaders> responseHeaders = restUtils.responseHeaders(url, method, paramObj);
		return responseHeaders;
	}
}
