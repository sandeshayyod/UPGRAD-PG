package com.upgrad.doctorservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.upgrad.doctorservice.controller.DoctorOnboardingController.*(..))")
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
