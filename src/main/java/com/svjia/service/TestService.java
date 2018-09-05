package com.svjia.service;

import com.svjia.common.ImpalaConnnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: service模板
 * @Auther: chenjw
 * @Date: 2018/9/5 10:37
 */
@Service("testService")
public class TestService {

    private Log logger = LogFactory.getLog(TestService.class);


    public Map<String,String> jdbcTest() throws Exception{
        Map<String,String> resMap = new HashMap<>();
        Connection connection = ImpalaConnnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select count(*) num from sms_kudu.totalorder limit 10");
        ResultSet rs = ps.executeQuery();
        try {
            while (rs.next()) {
                resMap.put("num", rs.getString("num"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  e;
        } finally {
            ImpalaConnnection.close(connection,ps,rs);
        }
        return resMap;
    }

    public static void main(String[] args) throws Exception {
        Map<String,String> resMap = new TestService().jdbcTest();
        for(Map.Entry en : resMap.entrySet()){
            System.out.println(en.getKey()+","+en.getValue());
        }

    }
}
