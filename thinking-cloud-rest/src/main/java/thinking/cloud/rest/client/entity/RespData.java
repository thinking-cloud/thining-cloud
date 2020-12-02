package thinking.cloud.rest.client.entity;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * 封装rest响应数据
 * @author think
 *
 * @param <R> 响应200+的数据对象
 */
@Data
public class RespData<R> {
	private HttpStatus status;
	private R data;
	private String error;
	
	public RespData(HttpStatus status, R data) {
		this.status = status;
		this.data = data;
	}

	public RespData(HttpStatus status, String error) {
		this.status = status;
		this.error = error;
	}

	public RespData() { }

}
