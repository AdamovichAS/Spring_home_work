package com.github.adamovichas.project.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class PostProcessor {

    @Pointcut("@annotation(com.github.adamovichas.project.annotation.Profiling)")
    public void checkTime(){

    }

    @Around("checkTime()")
    Object runCheckTime(ProceedingJoinPoint joinPoint){
        try {
            long start = System.currentTimeMillis();
            final Object proceed = joinPoint.proceed();
            long finish = System.currentTimeMillis();
            Long workTime = finish - start;
            System.out.println("Время работы метода " + joinPoint.getSignature().getName() + " - " + workTime);
            return proceed;
        }catch (Throwable throwable){
            throw new RuntimeException(throwable);
        }
    }
}
