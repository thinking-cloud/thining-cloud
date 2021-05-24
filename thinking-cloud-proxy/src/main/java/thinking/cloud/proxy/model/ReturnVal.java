package thinking.cloud.proxy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 标识返回值的对象
 * @author zhouxinke
 * @date 2021年5月21日
 */
@Data
@AllArgsConstructor
public class ReturnVal {
	private boolean isRun;
	private Object value;
}

