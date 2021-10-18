package com.myapplication.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

public abstract class CommonInterceptor {
	
	static final long MAX_BYTES_LOG_OUTPUT = 25000;
	
	public abstract Object aroundAdvise(ProceedingJoinPoint jp) throws Throwable;
	
}
