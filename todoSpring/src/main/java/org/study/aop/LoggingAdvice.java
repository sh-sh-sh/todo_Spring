package org.study.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//로깅을 위한 클래스
@Aspect
@Component
public class LoggingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	//service 패키지 안에 있는 메서드가 실행되면 실행하기 전후 시간을 비교해 걸린 시간을 로그에 출력한다.
	@Around("execution(* org.study.service.*.*(..))")
	public Object readLog(ProceedingJoinPoint pjp) throws Throwable {
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