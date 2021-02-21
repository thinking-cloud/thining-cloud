package thinking.cloud.rest.client.entity;

import org.springframework.http.HttpStatus;

import lombok.Builder;
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

}
