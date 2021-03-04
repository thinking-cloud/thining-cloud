package thinking.cloud.core.proxy;

import java.lang.annotation.Annotation;
import java.util.List;

import org.aspectj.lang.JoinPoint;

import lombok.extern.slf4j.Slf4j;
import thinking.cloud.api.entity.Model;
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
			
			//验证包
			String fullName = point.getSignature().toString();
			String[] basePackage = proxy.basePackage();
			for (String packageName : basePackage) {
				if (packageName.equals("") || fullName.indexOf(packageName)>=0) {
					flag = true;
					break;
				}
			}
			
			
			// 验证类
			f:if(flag) {
				Class[] proxyClassArray = proxy.proxyClass();
				for (Class proxyClass : proxyClassArray) {
					if (proxyClass.isAssignableFrom(point.getTarget().getClass())) {
						flag = true;
						break f;
					}
				}
				flag = false;
			}
			
			// 验证方法
			f:if(flag) {
				String[] proxyMethodNameArrays = proxy.proxyMethod();
				String methodName = point.getSignature().getName();
				for (String proxyMethodName : proxyMethodNameArrays) {
					if (proxyMethodName.equals("") || proxyMethodName.equals(methodName)) {
						flag = true;
						break f;
					}
				}
				flag = false;
			}
			if(flag) {
				return proxyHandler.isHandler(Model.getModel());
			}
		}

		return false;
	}

	protected <T extends ProxyHandler> void before(JoinPoint point, List<T> proxyHandler) {
		if (Model.getModel()==null||point.getTarget() instanceof ProxyHandler)
			return; // 如果被代理的是一个代理类 ,则不执行代理方法
		if (proxyHandler != null && proxyHandler.size() > 0) {
			for (ProxyHandler ph : proxyHandler) {
				Model.getModel().setPoint(point);
				boolean validate = validate(ph, point);
				log.info("x-request-id:{}  -proxy:{} - method:{} - {}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), validate);
				if (validate) {
					ph.handler(Model.getModel());
					log.info("x-request-id:{}  -proxy:{} - method:{} - {}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), "end");
				}
			}
		}
	}

	protected <T extends ProxyHandler> void afterRuturning(JoinPoint point, Object returnVal, List<T> proxyHandler) {
		if (Model.getModel()==null||point.getTarget() instanceof ProxyHandler)
			return; // 如果被代理的是一个代理类 ,则不执行代理方法
		if (proxyHandler != null && proxyHandler.size() > 0) {
			for (ProxyHandler ph : proxyHandler) {
				Model.getModel().setPoint(point);
				Model.getModel().setReturnObj(returnVal);
				boolean validate = validate(ph, point);
				log.info("x-request-id:{}  -proxy:{} - method:{} - {}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), validate);
				if (validate) {
					ph.handler(Model.getModel());
					log.info("x-request-id:{}  -proxy:{} - method:{} - {}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), "end");
				}
			}
		}
	}

	protected <T extends ProxyHandler> void afterThrow(JoinPoint point, Throwable throwable, List<T> proxyHandler) {
		if (Model.getModel()==null||point.getTarget() instanceof ProxyHandler)
			return; // 如果被代理的是一个代理类 ,则不执行代理方法
		if (proxyHandler != null && proxyHandler.size() > 0) {
			for (ProxyHandler ph : proxyHandler) {
				Model.getModel().setPoint(point);
				Model.getModel().setThrowable(throwable);
				boolean validate = validate(ph, point);
				log.info("x-request-id:{}  -proxy:{} - method:{} - {}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), validate);
				if (validate) {
					ph.handler(Model.getModel());
					log.info("x-request-id:{}  -proxy:{} - method:{} - {}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), "end");
				}
			}
		}
	}

	protected <T extends ProxyHandler> void after(JoinPoint point, List<T> proxyHandler) {
		if (Model.getModel()==null||point.getTarget() instanceof ProxyHandler)
			return; // 如果被代理的是一个代理类 ,则不执行代理方法
		if (proxyHandler != null && proxyHandler.size() > 0) {
			for (ProxyHandler ph : proxyHandler) {
				Model.getModel().setPoint(point);
				boolean validate = validate(ph, point);
				log.info("x-request-id:{}  -proxy:{} - method:{} - {}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), validate);
				if (validate) {
					ph.handler(Model.getModel());
					log.info("x-request-id:{}  -proxy:{} - method:{} - {}", Model.getModel().getXRequestId(), ph.getClass().getName(), point.getSignature(), "end");
				}
			}
		}
	}
}
