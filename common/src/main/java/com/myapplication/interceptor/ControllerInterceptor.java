package com.myapplication.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Aspect
@Configuration
public class ControllerInterceptor extends CommonInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

	@Override
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object aroundAdvise(ProceedingJoinPoint jp) throws Throwable {
		String reqPath = getRequestPath(jp);
		String serviceName = jp.getTarget().getClass().getSimpleName() + " " + reqPath;
		String rqString = getRequest(jp);
		Map<String, String> additionalInfo = new HashMap<String, String>();

		if (rqString != null) {
			if (rqString.getBytes("UTF-8").length <= MAX_BYTES_LOG_OUTPUT) {
				logger.info("INCOMING [" + serviceName + "]:" + System.lineSeparator() + rqString);
			} else {
				logger.info("INCOMING [" + serviceName + "]:" + System.lineSeparator() + "== Input content size is more than " + MAX_BYTES_LOG_OUTPUT + " bytes ==");
			}
		} else {
			logger.info("INCOMING [" + serviceName + "]:" + System.lineSeparator() + "== NULL ==");
		}
		

		Object returnObj = null;
		try {
			returnObj = jp.proceed();
		} catch (Exception e) {
			additionalInfo.put("Exception", e.getMessage());
			throw e;
		} finally {
			String rsString = getResponse(returnObj);

			if (rsString != null) {
				if (rsString.getBytes("UTF-8").length <= MAX_BYTES_LOG_OUTPUT) {
					logger.info("OUTGOING [" + serviceName + "]:" + System.lineSeparator()
							+ rsString);
				} else {
					logger.info("OUTGOING [" + serviceName + "]:" + System.lineSeparator()
							+ "== Output content size is more than " + MAX_BYTES_LOG_OUTPUT + " bytes ==");
				}
			} else {
				logger.info("OUTGOING [" + serviceName + "]:" + System.lineSeparator()
						+ "== NULL ==");
			}

		}

		return returnObj;
	}

	private String getResponse(Object obj) {
		String resp = null;
		if (obj != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			try {
				resp = mapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				logger.error("Failed to map response object \"" + obj + "\": " + e.getMessage());
			}
		}

		return resp;
	}

	private String getRequest(JoinPoint p) {
		String req = null;
		Object[] signatureArgs = p.getArgs();

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		if (signatureArgs != null && signatureArgs.length > 0) {
			for (Object object : signatureArgs) {
				try {
					if (object != null) {
						req = mapper.writeValueAsString(object);
					}
				} catch (JsonProcessingException e) {
					logger.error("Failed to map request object \"" + object + "\": " + e.getMessage());
				}

			}
		}

		return req;
	}

	public String getRequestPath(JoinPoint p) {
		String reqPath = null;
		try {
			MethodSignature signature = (MethodSignature) p.getSignature();
			RequestMapping reqMapping = signature.getMethod().getAnnotation(RequestMapping.class);
			reqPath = (reqMapping.value().length > 0) ? reqMapping.value()[0] : "";
		} catch (Exception e) {
			logger.error("Unable to get request path on method " + p.getTarget().getClass().getSimpleName() + "."
					+ p.getSignature().getName() + ": " + e.getMessage());
		}
		return reqPath;
	}

}
