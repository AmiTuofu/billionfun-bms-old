package com.billionfun.bms.product.mall.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspect {
	private final static Logger logger = LoggerFactory.getLogger(ServiceAspect.class);
	
	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	//
	@Pointcut("execution(* com.billionfun.bms.product.mall.service.impl.*.*(..))")
	public void aspect(){
		
	}
	
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 * 1
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint){
	}
	
	//配置环绕通知,使用在方法aspect()上注册的切入点
	//2
	@Around("aspect()")
	public Object around(JoinPoint joinPoint){
		long start = System.currentTimeMillis();
		try {
			Object obj = ((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
			return obj;
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
		}
		return null;
	}
	
	//配置后置通知,使用在方法aspect()上注册的切入点
	//3
	@After("aspect()")
	public void after(JoinPoint joinPoint){
	}
	
	//配置后置返回通知,使用在方法aspect()上注册的切入点
	//4
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint){
	}
		
		//配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut="aspect()", throwing="ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex){
		ex.printStackTrace();
		logger.error("afterThrow " + joinPoint + "\t" + ex.getMessage());
	}
}
