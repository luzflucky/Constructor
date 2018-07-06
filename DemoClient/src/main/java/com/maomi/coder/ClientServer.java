package com.maomi.coder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by lucky on 2018/6/28.
 */
@EnableEurekaClient
@SpringBootApplication
public class ClientServer {
    public static void main(String[] args) {
        SpringApplication.run(ClientServer.class,args);
    }
}
