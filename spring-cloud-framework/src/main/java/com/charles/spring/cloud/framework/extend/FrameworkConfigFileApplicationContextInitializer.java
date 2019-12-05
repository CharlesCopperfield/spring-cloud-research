package com.charles.spring.cloud.framework.extend;

import com.charles.spring.cloud.framework.exception.SpringStartException;
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

    public static final String FRAMEWORK_COMMON_FILE_PREFIX = "charles-framework-common";
    public static final String FRAMEWORK_COMMON_FILE_SUFFIX = ".yml";
    public static final String DEFAULT_FRAMEWORK_COMMON_FILE = FRAMEWORK_COMMON_FILE_PREFIX + FRAMEWORK_COMMON_FILE_SUFFIX;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();

        int size = activeProfiles.length + 1;
        String[] frameworkCommonFiles = new String[size];
        for (int i = 0; i < size; i++) {
            // 这个顺序可以保证后面的profile优先级比前面的高
            if (i != size - 1) {
                // eg. charles-framework-common-dev.yml
                frameworkCommonFiles[i] = FRAMEWORK_COMMON_FILE_PREFIX + "-" + activeProfiles[activeProfiles.length - 1 - i] + FRAMEWORK_COMMON_FILE_SUFFIX;
            } else {
                frameworkCommonFiles[i] = DEFAULT_FRAMEWORK_COMMON_FILE;
            }
        }

        for (String configFileName : frameworkCommonFiles) {
            ClassPathResource classPathResource = new ClassPathResource(configFileName);
            if (!classPathResource.exists()) {
                return;
            }
            try {
                YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
                List<PropertySource<?>> yamlPropertySources = loader.load(configFileName, classPathResource);
                if (!CollectionUtils.isEmpty(yamlPropertySources)) {
                    MutablePropertySources propertySources = environment.getPropertySources();
                    yamlPropertySources.forEach(propertySources::addLast);
                }
            } catch (IOException e) {
                throw new SpringStartException(e);
            }
        }

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
