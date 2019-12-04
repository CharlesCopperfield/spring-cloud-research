package com.charles.spring.cloud.framework.aop;

import brave.Tracer;
import com.charles.spring.cloud.framework.utils.JsonUtils;
import com.charles.spring.cloud.framework.utils.ReturnMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Autowired
    private Tracer tracer;

    @Value("${spring.profiles.active}")
    private String profile;

    @Pointcut("execution(public com.charles.spring.cloud.framework.utils.ReturnMessage com.charles.spring.cloud..controller.*Controller.*(..))")
    public void returnMessagePointCut() {
    }

    /**
     * 功能:
     * 1.日志记录
     * 2.异常拦截并统一处理
     * 3.返回值设置traceId
     */
    @Around("returnMessagePointCut()")
    public ReturnMessage doAround(ProceedingJoinPoint joinPoint) {
        // 准备下面要用到的参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.toString();
        Object[] args = joinPoint.getArgs();

        // 开始执行
        ReturnMessage returnMessage = null;
        long start = System.currentTimeMillis();
        try {
            logger.info("begin call controller method({})", methodName);
            returnMessage = (ReturnMessage) joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("exception to call controller method(" + methodName + "), the params=" +
                    JsonUtils.getObjToString(args), throwable);
            // 生产环境不返回报错信息
            returnMessage = ReturnMessage.fail("prod".equalsIgnoreCase(profile) ? null : throwable);
        } finally {
            logger.info("controller cost {} ms, end execute controller method({}).", (System.currentTimeMillis() - start), methodName);
            String traceIdString = tracer.currentSpan().context().traceIdString();
            returnMessage.setTraceId(traceIdString);
        }
        return returnMessage;
    }


}

