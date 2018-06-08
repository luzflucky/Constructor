package com.maomi.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 1 on 2018/3/22.
 * @author zflu
 * @date 2018年3月22日 09:35:13
 * Spring boot 启动类
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.maomi")
@MapperScan(basePackages = "com.maomi.mapper")
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class);
    }
}
