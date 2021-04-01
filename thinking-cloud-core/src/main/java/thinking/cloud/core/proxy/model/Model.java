package thinking.cloud.core.proxy.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * model类
 * @author think
 */
@Data
@Builder
@Slf4j
public class Model<T> {	
	private static ThreadLocal<Model> MODEL = new ThreadLocal<>();
	
	public static Model getModel() {
		Model m = MODEL.get();
		if(m!=null) {
			MODEL.set(m);
			log.debug("x-request-id:{} 获取model",m.getXRequestId());
		}else {
			log.debug("获取model失败, 当前线程不存在model");
		}
		return m;
	}
	
	public static void setModel(Model m) {
		Model modelCatch ;
		if((modelCatch = MODEL.get())!=null) {
			log.debug("x-request-id:{} 重置model",modelCatch.getXRequestId());
		}else {
			log.debug("x-request-id:{} 设置model",m.getXRequestId());
		}
		MODEL.set(m);
	}
	
	public static Model clear() {
		Model m = MODEL.get();
		MODEL.remove();
		if(m==null) {
			log.debug("当前线程model不存在");
		}else {
			log.debug("x-request-id:{} 清理model",m.getXRequestId());
		}
		return m;
	}
		
	private long timestamp;
	private String xRequestId;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private JoinPoint point;
	private Object returnObj;
	private Throwable throwable;
	private T cache;
	
	
	public String getMethodName() {
		return point.getSignature().getName();
	}
	
	public Object[] getParams() {
		return point.getArgs();
	}
}
