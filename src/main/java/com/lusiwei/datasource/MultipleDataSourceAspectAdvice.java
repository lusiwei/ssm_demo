package com.lusiwei.datasource;

import com.lusiwei.dao.TeacherMapper;
import com.lusiwei.dao.TeacherMapper2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MultipleDataSourceAspectAdvice {
    @Around("execution(* com.lusiwei.dao.*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("进入");
        if (joinPoint.getTarget() instanceof TeacherMapper) {
            MultipleDataSource.setDataSourceKey("ssmDataSource");
        } else if (joinPoint.getTarget() instanceof TeacherMapper2) {
            MultipleDataSource.setDataSourceKey("ssm2DataSource");
        }
        return joinPoint.proceed();
    }
}
