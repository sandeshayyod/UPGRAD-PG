package com.example.aopdemo.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Around("execution(* com.example.aopdemo..*(..))")
    public Object applyLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String classname = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("In {}, entering {} at time: {}", classname, methodName, new Date(start));
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long totalTime = end - start;
        log.info("In {}, exiting {} at time: {}, total time taken: {}ms", classname, methodName, new Date(end), totalTime);
        return result;
    }

}
