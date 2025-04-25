package com.taylor.common.web.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 请求日志切面
 *
 * @author loveCamille
 * @date 2025-04-25 14:05:25
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class RequestLogAspect {

    private final ObjectMapper objectMapper;

    /** 切入所有 @RestController 或 @Controller 类的 public 方法 */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Controller *)")
    public void controllerBean() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("controllerBean() && publicMethod()")
    public void logPointcut() {}

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取 HttpServletRequest
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attrs != null;
        HttpServletRequest request = attrs.getRequest();

        // 打印请求信息
        String url = request.getRequestURI();
        String httpMethod = request.getMethod();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        // 利用 Stream 过滤指定类型
        List<Object> args = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> {
                    if (arg == null) return false;
                    return !(arg instanceof HttpServletRequest
                            || arg instanceof HttpServletResponse
                            || arg instanceof MultipartFile
                            || arg instanceof BindingResult);
                })
                .collect(Collectors.toList());
        String paramsJson;
        try {
            paramsJson = objectMapper.writeValueAsString(args);
        } catch (Exception e) {
            paramsJson = "[参数序列化失败]";
        }

        log.info("[请求开始] url={}, http_method={}, 执行方法={}.{}(), 入参={}",
                url, httpMethod, className, methodName, paramsJson);

        // 执行目标方法
        Object result = joinPoint.proceed();

        log.info("[请求结束] {}.{}() 执行完毕，返回={}",
                className, methodName,
                objectMapper.writeValueAsString(result));

        return result;
    }

}
