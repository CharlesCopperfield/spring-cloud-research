package com.charles.spring.cloud.framework;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.*;

/**
 * Hello world!
 */
@Configuration
@ComponentScan
public class SpringCloudFrameworkApplication {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
