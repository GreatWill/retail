package com.svjia.controller;

import com.svjia.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/5 10:49
 */
@Controller
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    private Log logger = LogFactory.getLog(TestController.class);

    /**
     * Description:test
     * @auther: chenjw
     * @date: 2018/9/5 11:19
     */
    @RequestMapping("/testMap")
    @ResponseBody
    public Map<String, String> testMap(){
        Map<String, String> map = new HashMap<>();
        try {
            map = testService.jdbcTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
