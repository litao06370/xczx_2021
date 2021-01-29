package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 微服务启动类
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.xuecheng.framework.domain.cms"})
@ComponentScan(basePackages = {"com.xuecheng.api"})
@ComponentScan(basePackages = {"com.xuecheng.manage_cms"})
@ComponentScan(basePackages = {"com.xuecheng.framework"})
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }


}
