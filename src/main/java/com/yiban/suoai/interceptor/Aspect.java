/*
package com.yiban.suoai.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    //匹配com.wechat.lease.controller包及其子包下的所有类的所有方法
    @Pointcut("execution(* com.yiban.suoai.controller..*.*(..))")
    public void executeService() {

    }

    */
/**
     * 在每次方法执行后，都要查看一下  redis 有没有通知
     * 环绕通知： 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     *//*

    @Around("execution(* com.yiban.suoai.controller..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint point) {
        //System.out.println("环绕通知被执行，目标方法执行之前");
        try {
            Object obj = point.proceed(); //执行方法
            //System.out.println("环绕通知被执行，目标方法执行之后");
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
*/
