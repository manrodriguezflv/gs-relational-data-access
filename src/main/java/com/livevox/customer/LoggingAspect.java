package com.livevox.customer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

    @Around("execution(* com.livevox.customer.CustomerServiceImpl.*(..))")
    public Object logCall(ProceedingJoinPoint pjp) throws Throwable {
        Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
        String target = pjp.getSignature().getName();
        log.debug("before {} call", target);
        Object obj = pjp.proceed();
        log.debug("after {} call", target);
        return obj;
    }
}
