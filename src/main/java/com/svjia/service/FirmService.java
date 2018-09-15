package com.svjia.service;

import com.svjia.common.FirmSql;
import com.svjia.common.ImpalaConnnection;
import com.svjia.common.PeinConstants;
import com.svjia.common.PeinUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: service
 * @Auther: chenjw
 * @Date: 2018/9/5 10:37
 */
@Service("firmService")
public class FirmService {

    private Log logger = LogFactory.getLog(FirmService.class);


    /**
     * Description: 总量
     * @auther: chenjw
     * @date: 2018/9/12 9:29
     */
    public Map<String,String> Total() throws Exception{
        Map<String,String> resMap = new HashMap<>();
        Connection connection = ImpalaConnnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(FirmSql.TOTAL_ALL);
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

    /**
     * Description: 历史时间新增量
     * @param type 区分去年,上季度,上个月,上周,昨日
     * @auther: chenjw
     * @date: 2018/9/12 9:29
     */
    public Map<String,String> LastIncrement(String type) throws Exception{
        Map<String,String> resMap = new HashMap<>();
        Connection connection = ImpalaConnnection.getConnection();
        PreparedStatement ps = getIncrementPsByType(type, connection);
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

    /**
     * Description: 新增量环比
     * @param type 区分去年,上季度,上个月,上周,昨日
     * @auther: chenjw
     * @date: 2018/9/12 9:29
     */
    public Map<String,String> LastIncrementRingRate(String type) throws Exception{
        Map<String,String> resMap = new HashMap<>();
        Connection connection = ImpalaConnnection.getConnection();
        PreparedStatement ps = getRatePsByType(type, PeinConstants.RING_RATE, connection);
        ResultSet rs = ps.executeQuery();
        try {
            while (rs.next()) {
                double res = rs.getDouble("res");
                if(res == -5){
                    resMap.put("result","NaN");
                }else{
                    res =new BigDecimal((res-1)*100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    resMap.put("result",res+"%");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  e;
        } finally {
            ImpalaConnnection.close(connection,ps,rs);
        }
        return resMap;
    }

    /**
     * Description: 新增量同比
     * @param type 区分去年,上季度,上个月,上周,昨日
     * @auther: chenjw
     * @date: 2018/9/12 9:29
     */
    public Map<String,String> LastIncrementYearRate(String type) throws Exception{
        Map<String,String> resMap = new HashMap<>();
        Connection connection = ImpalaConnnection.getConnection();
        PreparedStatement ps = getRatePsByType(type, PeinConstants.YEAR_RATE, connection);
        ResultSet rs = ps.executeQuery();
        try {
            while (rs.next()) {
                double res = rs.getDouble("res");
                if(res == -5){
                    resMap.put("result","NaN");
                }else{
                    res =new BigDecimal((res-1)*100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    resMap.put("result",res+"%");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  e;
        } finally {
            ImpalaConnnection.close(connection,ps,rs);
        }
        return resMap;
    }

    /***private***/
    /**
     * Description: 新增量,根据参数获取不同时间的PreparedStatement
     * @auther: chenjw
     * @date: 2018/9/12 9:40
     */
    private PreparedStatement getIncrementPsByType(String type, Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(FirmSql.TOTAL_BY_TIME);
        String[] dateArgs = PeinUtil.createPreDate(type);
        ps.setString(1, dateArgs[0]);
        ps.setString(2, dateArgs[1]);
        return ps;
    }


    /**
     * Description: 根据type返回对应的ps,全国环比同比
     *              主要是根据不同sql设置不同参数
     * @auther: chenjw
     * @date: 2018/9/12 9:40
     */
    private  PreparedStatement getRatePsByType(String type, String rate, Connection conn) throws Exception{
        PreparedStatement ps = conn.prepareStatement(FirmSql.RATE_BY_TIME);
        String[] preDate = PeinUtil.createPreDate(type);
        String[] dateRateArg;
        if(PeinConstants.RING_RATE.equals(rate)){
            dateRateArg = PeinUtil.createPrePreDate(type);
        }else{
            dateRateArg = PeinUtil.createLastPreDate(type);
        }
        ps.setString(1,preDate[0]);
        ps.setString(2,preDate[1]);
        ps.setString(3,dateRateArg[0]);
        ps.setString(4,dateRateArg[1]);
        return ps;
    }



}
