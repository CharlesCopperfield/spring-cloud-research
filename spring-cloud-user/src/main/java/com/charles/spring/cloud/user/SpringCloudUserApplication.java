package com.charles.spring.cloud.user;

import com.charles.spring.cloud.framework.SpringCloudFrameworkApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
@Import(SpringCloudFrameworkApplication.class)
@MapperScan("com.charles.spring.cloud.user.mapper")
public class SpringCloudUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudUserApplication.class, args);
    }

    @Value("${test.sort}")
    private String testSort;

    @Value("${pagehelper.helperDialect}")
    private String helperDialect;

    @GetMapping("/hello")
    public String hello() {
        return "hello " + testSort + "--" + helperDialect;
    }
}
