package com.svjia.controller;

import com.svjia.common.PeinConstants;
import com.svjia.common.TurnoverSql;
import com.svjia.service.TestService;
import com.svjia.service.TurnoverService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/turnover")
public class TurnoverController {

    @Autowired
    private TurnoverService turnoverService;

    private Log logger = LogFactory.getLog(TurnoverController.class);

    /**
     * Description:总量查询
     * @param type 区分是历史总量,还是last新增量
     * @auther: chenjw
     * @date: 2018/9/12 9:51
     */
    @RequestMapping("/amount")
    @ResponseBody
    public Map<String,String> amount(@RequestParam("type")String type){
        Map<String,String> map;
        try{
            if(type == null || "".equals(type.trim())){
                map = turnoverService.Total();
            }else{
                map = turnoverService.LastIncrement(type);
            }
        }catch(Exception e){
            logger.error("amount查询失败"+e.getMessage());
            map = null;
        }
        return map;
    }


    /**
     * Description: 环比同比
     * @auther: chenjw
     * @date: 2018/9/12 9:56
     */
    @RequestMapping("/rate")
    @ResponseBody
    public Map<String,String> rate(@RequestParam("type")String type,@RequestParam("rate")String rate){
        Map<String,String> map;
        try{
            switch (rate){
                case PeinConstants.RING_RATE:
                    map = turnoverService.LastIncrementRingRate(type);
                    break;
                case PeinConstants.YEAR_RATE:
                    map = turnoverService.LastIncrementYearRate(type);
                    break;
                    default:
                        logger.error("参数错误"+type);
                        map = null;
                        break;
            }
        }catch(Exception e){
            logger.error("rate查询失败"+e.getMessage());
            map = null;
        }
        return map;
    }


}
