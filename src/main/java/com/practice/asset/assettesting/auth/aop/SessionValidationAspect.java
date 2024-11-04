package com.practice.asset.assettesting.auth.aop;


import com.practice.asset.assettesting.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;


@Aspect
@Component
public class SessionValidationAspect {
    @Pointcut("@annotation(requestMapping)")
    public void apiEndpoints(RequestMapping requestMapping) {}

    @Autowired
    JwtUtil jwtUtil;

    @Around("apiEndpoints(requestMapping)")
    public Object validateSession(ProceedingJoinPoint joinPoint, RequestMapping requestMapping) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader("Authorization");
        String userName = request.getHeader("userName");

        if (token == null || !isValidToken(token, userName)) {
            throw new RuntimeException("Invalid session. Please log in again.");
        }

        // Proceed with the actual method call
        return joinPoint.proceed();
    }

    @Pointcut("execution(* com.practice.asset.assettesting.controller..*(.., @org.springframework.web.bind.annotation.RequestBody (*), ..))")
    public void requestBodyMethods() {}

    @Before("requestBodyMethods()")
    public void logRequestBody(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // Log the method name
        System.out.println("Intercepted method: " + method.getName());
    }
   /* @Pointcut("execution(* com.practice.asset.assettesting.controller..*(.., @org.springframework.web.bind.annotation.RequestBody (*), ..))")
    public void requestBodyMethods() {}

    @Before("requestBodyMethods()")
    public void logRequestBody(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // Log the method name
        System.out.println("Intercepted method: " + method.getName());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader("Authorization");
        String userName = request.getHeader("userName");

        if (token == null || !isValidToken(token, userName)) {
            throw new RuntimeException("Invalid session. Please log in again.");
        }

        // You can add additional logic here, like validating the request body
    }*/


    private boolean isValidToken(String token, String userName) {
        // Implement your token validation logic (e.g., decode JWT and check validity)
        return jwtUtil.validateToken(token, userName);
//        return true; // Replace with actual validation logic
    }
}

