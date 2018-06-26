package com.maomi.coder.WebController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lucky on 2018/6/26.
 */
@RequestMapping("/test")
@RestController
public class WebController {

    Logger logger = LoggerFactory.getLogger(WebController.class);

    @RequestMapping("/web")
    public @ResponseBody String queryWeb(){
        logger.info("-------------------------");
        return "success";
    }
}
