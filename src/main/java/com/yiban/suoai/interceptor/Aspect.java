package com.yiban.suoai.interceptor;

import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.UserService;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;


    //匹配com.wechat.lease.controller包及其子包下的所有类的所有方法
    @Pointcut("execution(* com.yiban.suoai.controller..*.*(..))")
    public void executeService() {

    }
/*
     *
     * 在每次方法执行后，都要查看一下  redis 有没有通知
     * 环绕通知： 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */

   //@Around("execution(* com.yiban.suoai.controller..*.*(..))")
    @Around("execution(* com.yiban.suoai.controller..*.*(..))")
    @AfterThrowing("execution(* com.yiban.suoai.controller..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint point) throws Throwable {
        //System.out.println("环绕通知被执行，目标方法执行之前");
        try {
            Object obj = point.proceed(); //执行方法
            //System.out.println("环绕通知被执行，目标方法执行之后");
            String classType = point.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            String methodName = point.getSignature().getName(); //获取方法名称
            List<String> s=new ArrayList<>();
            //添加需要获得经验的方法名
            s.add("expressionPut");
            s.add("commentPut");
            s.add("givelike");
            s.add("getWeekWord2");
            s.add("inform");
            s.add("writeLetter");
            s.add("getSpaceLetter");
            s.add("comment");
            s.add("ordinaryMatch");
            s.add("sendinvitation");
            for(String s1:s){
                if(methodName.equals(s1)){
                    Object[] args = point.getArgs();//参数
                    userService.addExperience(redisService.getUserId((String) args[0]),1);
                }
            }
           // System.out.println(methodName);
            return obj;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }


    /**
     * 通过反射机制 获取被切参数名以及参数值
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws NotFoundException
     */
    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException, NotFoundException {
        Map<String, Object> map = new HashMap<String, Object>();

        ClassPool pool = ClassPool.getDefault();
        //ClassClassPath classPath = new ClassClassPath(this.getClass());
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        // String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            map.put(attr.variableName(i + pos), args[i]);//paramNames即参数名
        }
        return map;
    }

}
