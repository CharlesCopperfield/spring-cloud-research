package com.charles.spring.cloud.framework.extend;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/12/2019 7:24 PM
 */
public class FrameworkConfigFileApplicationContextInitializer implements Ordered, ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        ClassPathResource classPathResource = new ClassPathResource("charles-framework-common.yml");
        if (!classPathResource.exists()) {
            return;
        }
        try {
            YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
            List<PropertySource<?>> yamlPropertySources = loader.load("charles-framework-common", classPathResource);
            if (!CollectionUtils.isEmpty(yamlPropertySources)) {
                ConfigurableEnvironment environment = applicationContext.getEnvironment();
                MutablePropertySources propertySources = environment.getPropertySources();
                yamlPropertySources.forEach(propertySources::addLast);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
