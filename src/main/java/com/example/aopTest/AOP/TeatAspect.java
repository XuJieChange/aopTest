package com.example.aopTest.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

@Aspect//視為切面
@Component//建立成元件
/**
 * 可以將切面理解為，切點跟通知所在的類就是一個切面
 */
public class TeatAspect {
    //初始化日誌記錄器 (logger)
   private final static Logger log=Logger.getLogger(String.valueOf(TeatAspect.class));
    /**
     * 在方法上方使用@Pointcut，表示切入點，value值為切入點表達是 下面幾格常用寫法
     * 1.execution(& *(..))--匹配本項目的所有方法
     * 2.execution(* com.example.aopTest.controller..*(..))--匹配com.example.aopTest.controller包下的所有方法
     * 3.execution(* com.example.aopTest.controller.DemoController())--匹配com.example.aopTest.controller.DemoController包下DemoController類的Test方法
     */
    @Pointcut(value = "execution(* com.example.aopTest.controller..*(..))")
    public void aop(){}
    /**
     * Before、After、AfterReturning、AfterThrowing、Around五種通知
     * 參數填寫切入點方法
     */
    @Before("aop()")
    public void before(){
        log.info("前置通知");
    }
    @After("aop()")
    public void after(){
        log.info("後置通知");
    }
    @AfterReturning(value = "aop()", returning = "re")
    public void afterReturning(Object re){
        log.info("返回通知+"+"---"+re);
    }
    @AfterThrowing(pointcut = "aop()",throwing = "ex")
    public void afterThrowing(Exception ex){
        log.info("異常通知"+"---"+ex.getMessage());
    }

    /**
     * 環繞通知將覆蓋原方法，可以在原方法執行前後添加操作，只通過環繞通知實現其他四種通知功能
     * 以下環繞通知，模擬了四種通知
     * ProceedingJoinPoint繼承了JoinPoint類，本質上都是獲取連接點，前者增加了proceed方法，可以用於運行連接點
     */
    @Around("aop()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        try{
            log.info("環繞通知--前置通知");
            Object result=proceedingJoinPoint.proceed();
            log.info("環繞通知--返回通知--"+result);
        }catch (Exception e){
            log.info("環繞通知--異常通知"+e.getMessage());
        }finally {
            log.info("環繞通知--後置通知");
        }
    }
}
