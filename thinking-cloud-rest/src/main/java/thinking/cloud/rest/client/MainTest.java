package thinking.cloud.rest.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.catalina.webresources.FileResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * <P>
 * </P>
 * @author zhouxinke
 * @date 2020年11月19日
 */
public class MainTest {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		File exchange = restTemplate.execute("http://10.40.30.46:7990/rest/api/1.0/projects/WT/repos/web-test-repository/raw/404.xml", HttpMethod.POST, new RequestCallback() {
			
			@Override
			public void doWithRequest(ClientHttpRequest request) throws IOException {
				request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46YWRtaW4=");
				
			}
		},
				clientHttpResponse-> {
					File ret = File.createTempFile("download", "tmp");	
				    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));	
				    return ret;	

				});
		System.out.println(exchange.getAbsolutePath());
	}
}
