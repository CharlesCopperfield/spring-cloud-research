package com.charles.spring.cloud.framework.aop;

import com.charles.spring.cloud.framework.exception.FeignCallException;
import com.charles.spring.cloud.framework.utils.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @author: st-wgsf-zengs
 * @time: 12/4/2019 12:53 PM
 */
@Aspect
@Configuration
public class FeignServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.charles.spring.cloud..feign.*FeignService.*(..))")
    public void feignServicePointCut() {
    }

    /**
     * 功能:
     * 1.日志记录
     * 2.异常拦截并统一处理
     * 3.返回值设置traceId
     */
    @Around("feignServicePointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        // 准备下面要用到的参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.toString();
        Object[] args = joinPoint.getArgs();

        // 开始执行
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            logger.info("begin call feignService method({})", methodName);
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error("exception to call feignService method(" + methodName + "), the params=" +
                    JsonUtils.getObjToString(args));
            throw new FeignCallException(e);
        } finally {
            logger.info("feignService cost {} ms, end execute feignService method({}), the result={},", (System.currentTimeMillis() - start), methodName, result.toString());
        }
        return result;
    }


}
