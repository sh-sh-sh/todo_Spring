package org.study.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
//	@After("execution(* org.study.service.*.*(..))")
//	public void startLog(JoinPoint jp) {
//		logger.info("startLog ----->");
//		 String sg=jp.getSignature().toShortString();
//		if(!sg.contains("get")) {
//			logger.info(jp.getSignature().toShortString());
//		}
////		logger.info(Arrays.toString(jp.getArgs()));
//	}
	
	@Around("execution(* org.study.service.*.*(..))")
	public Object readLog(ProceedingJoinPoint pjp) throws Throwable {
//		logger.info("startLog ----->");
		long startTime = System.currentTimeMillis();
		try {
			Object obj = pjp.proceed();
			return obj;
		}finally {
			long endTime = System.currentTimeMillis();
			
			logger.info(pjp.getSignature().toShortString() + ":" + (endTime - startTime) + "(ms)");
		}
		
		
	}
}