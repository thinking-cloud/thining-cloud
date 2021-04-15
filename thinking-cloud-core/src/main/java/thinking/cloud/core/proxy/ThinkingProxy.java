package thinking.cloud.core.proxy;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.TreeSet;

import org.aspectj.lang.JoinPoint;

import lombok.extern.slf4j.Slf4j;
import thinking.cloud.core.proxy.model.Model;
import thinking.cloud.utils.reflect.ReflectUtils;


/**
 * 分类执行各层级代理的顶级类
 * @author think
 * @date 2021年2月22日
 */
@Slf4j
public abstract class ThinkingProxy {

	

	/**
	 * 验证被代理的方法是否合法
	 * 
	 * @param proxyHandler
	 * @param point
	 * @return true 验证成功
	 */
	protected boolean validate(ProxyHandler proxyHandler, JoinPoint point) {
		// 验证被代理的方法是否为指定的类及方法
		Annotation annotation = ReflectUtils.annotationBySupperClass(proxyHandler.getClass(),Proxy.class);
		if (annotation != null) {
			boolean flag = false;
			Proxy proxy = (Proxy) annotation;
			
			// 包与类必须配置一个
			String[] basePackage = proxy.basePackage();
			Class[] proxyClassArray = proxy.proxyClass();
			
			if(basePackage.length==1 && "".equals(basePackage[0]) && proxyClassArray.length==1 && Object.class == proxyClassArray[0]) {
				return false;
			}
			
			//验证包
			String fullName = point.getSignature().toString();
			for (String packageName : basePackage) {
				if (packageName.equals("") || fullName.indexOf(packageName)>=0) {
					flag = true;
					break;
				}
			}
			
			// 验证类
			if(flag) {
				for (Class proxyClass : proxyClassArray) {
					flag = false;
					if (proxyClass.isAssignableFrom(point.getTarget().getClass())) {
						flag = true;
						break;
					}
				}
				
			}
			
			// 验证方法
			if(flag) {
				String[] proxyMethodNameArrays = proxy.proxyMethod();
				String methodName = point.getSignature().getName();
				for (String proxyMethodName : proxyMethodNameArrays) {
					flag = false;
					if (proxyMethodName.equals("") || proxyMethodName.equals(methodName)) {
						flag = true;
						break;
					}
				}
			}
			if(flag) {
				return proxyHandler.isHandler(Model.getModel());
			}
		}

		return false;
	}

	protected <T extends ProxyHandler> void before(JoinPoint point, List<T> proxyHandler) {
		if (Model.getModel()==null||point.getTarget() instanceof ProxyHandler) return; // 如果被代理的是一个代理类 ,则不执行代理方法
		if (proxyHandler != null && proxyHandler.size() > 0) {
			long start = 0;
			long end = 0;
			for (ProxyHandler ph : sort(proxyHandler)) {
				Model.getModel().setPoint(point);
				boolean validate = validate(ph, point);
				log.info("x-request-id:{}  -proxy:{} - method:{} is-run:{}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), validate);
				if (validate) {
					start = System.currentTimeMillis();
					ph.handler(Model.getModel());
					end = System.currentTimeMillis();
					log.info("x-request-id:{}  -proxy:{} - method:{} time-consuming:{}ms", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(),end-start);
				}
			}
		}
	}

	protected <T extends ProxyHandler> void afterRuturning(JoinPoint point, Object returnVal, List<T> proxyHandler) {
		if (Model.getModel()==null||point.getTarget() instanceof ProxyHandler) return; // 如果被代理的是一个代理类 ,则不执行代理方法
		if (proxyHandler != null && proxyHandler.size() > 0) {
			long start = 0;
			long end = 0;
			for (ProxyHandler ph : sort(proxyHandler)) {
				Model.getModel().setPoint(point);
				Model.getModel().setReturnObj(returnVal);
				boolean validate = validate(ph, point);
				log.info("x-request-id:{}  -proxy:{} - method:{} is-run:{}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), validate);
				if (validate) {
					start = System.currentTimeMillis();
					ph.handler(Model.getModel());
					end = System.currentTimeMillis();
					log.info("x-request-id:{}  -proxy:{} - method:{} time-consuming:{}ms", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(),end-start);
				}
			}
		}
	}

	protected <T extends ProxyHandler> void afterThrow(JoinPoint point, Throwable throwable, List<T> proxyHandler) {
		if (Model.getModel()==null||point.getTarget() instanceof ProxyHandler)
			return; // 如果被代理的是一个代理类 ,则不执行代理方法
		if (proxyHandler != null && proxyHandler.size() > 0) {
			long start = 0;
			long end = 0;
			for (ProxyHandler ph : sort(proxyHandler)) {
				Model.getModel().setPoint(point);
				Model.getModel().setThrowable(throwable);
				boolean validate = validate(ph, point);
				log.info("x-request-id:{}  -proxy:{} - method:{} is-run:{}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), validate);
				if (validate) {
					start = System.currentTimeMillis();
					ph.handler(Model.getModel());
					end = System.currentTimeMillis();
					log.info("x-request-id:{}  -proxy:{} - method:{} time-consuming:{}ms", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(),end-start);
				}
			}
		}
	}

	protected <T extends ProxyHandler> void after(JoinPoint point, List<T> proxyHandler) {
		if (Model.getModel()==null||point.getTarget() instanceof ProxyHandler) return; // 如果被代理的是一个代理类 ,则不执行代理方法
		if (proxyHandler != null && proxyHandler.size() > 0) {
			long start = 0;
			long end = 0;
			for (ProxyHandler ph : sort(proxyHandler)) {
				Model.getModel().setPoint(point);
				boolean validate = validate(ph, point);
				log.info("x-request-id:{}  -proxy:{} - method:{} is-run:{}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), validate);
				if (validate) {
					start = System.currentTimeMillis();
					ph.handler(Model.getModel());
					end = System.currentTimeMillis();
					log.info("x-request-id:{}  -proxy:{} - method:{} time-consuming:{}ms", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(),end-start);
				}
			}
		}
	}
	
	/**
	 * 对代理进行排序的
	 * @param <T>
	 * @param proxyHandler
	 * @return
	 */
	private  <T extends Comparable<ProxyHandler>>  TreeSet<T> sort(List<T> proxyHandler) {
		TreeSet<T> treeSet = new TreeSet<>();
		for (T t : proxyHandler) {
			treeSet.add(t);
		}
		return treeSet;
	}
}
