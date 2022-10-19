package com.example.demo.aop;

import com.example.demo.util.TenantContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TenantFilterAspect
{
    @PersistenceContext
    private EntityManager entityManager;

    @Around("@annotation(com.example.demo.annotation.EnableFilter)")
    public Object doProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            final String tenantId = TenantContext.getTenantId();
            if (tenantId != null) {
               Filter filter = entityManager.unwrap(Session.class).enableFilter("tenantFilter");
               filter.setParameter("tenantId", tenantId);
            }
            return joinPoint.proceed();
        }catch (Throwable ex){
            ex.printStackTrace();
            throw ex;
        }finally {
            entityManager.unwrap(Session.class).disableFilter("filterByHotelCode");
        }
    }
}
