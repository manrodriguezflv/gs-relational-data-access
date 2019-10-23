package com.livevox.customer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.livevox.customer.CustomerController.*(..))")
    public Object logCall(ProceedingJoinPoint pjp) throws Throwable {
        LOG.debug("before call");
        Object obj = pjp.proceed();
        LOG.debug("after call");
        return obj;
    }
}
