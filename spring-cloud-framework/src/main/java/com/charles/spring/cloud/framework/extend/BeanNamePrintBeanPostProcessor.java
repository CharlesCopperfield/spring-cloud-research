package com.charles.spring.cloud.framework.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: st-wgsf-zengs
 * @time: 12/4/2019 8:32 AM
 */
@Component
public class BeanNamePrintBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanNamePrintBeanPostProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("----------beanName={}, beanClass={}", beanName, bean.getClass().getName());
        return bean;
    }

}
