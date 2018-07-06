package com.maomi.coder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lucky on 2018/6/28.
 */
@RestController
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String queryDemo(){
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        logger.info("serverPort:"+instance.getPort()+"host: "+instance.getHost()+"server_id"+instance.getServiceId());
        return "hello-server";
    }
}
