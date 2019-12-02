package com.charles.spring.cloud.goods;

import com.charles.spring.cloud.framework.SpringCloudFrameworkApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import(SpringCloudFrameworkApplication.class)
@MapperScan("com.charles.spring.cloud.goods.mapper")
public class SpringCloudGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGoodsApplication.class, args);
    }
}
