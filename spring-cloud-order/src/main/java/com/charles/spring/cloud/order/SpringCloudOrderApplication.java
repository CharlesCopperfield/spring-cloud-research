package com.charles.spring.cloud.order;

import com.charles.spring.cloud.framework.SpringCloudFrameworkApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import(SpringCloudFrameworkApplication.class)
@MapperScan("com.charles.spring.cloud.order.mapper")
public class SpringCloudOrderApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCloudOrderApplication.class, args);
        String[] names = context.getBeanDefinitionNames();
        Stream.of(names).map(n -> "----------beanName=" + n).forEach(System.out::println);
    }
}
