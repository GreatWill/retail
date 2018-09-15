package com.svjia.common;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/12 09:26
 */
public interface TurnoverSql {

    /**
     * 总量,由时间维度划分
     */
    //所有
    String TOTAL_ALL = "select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 \n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')";

    String TOTAL_BY_TIME = "select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')";

    //年
    String TOTAL_YEAR = "SELECT isnull(sum(b_value),0) num FROM retail_kudu.turnover where b_year = ?";

    //季度
    String TOTAL_QUARTER= "SELECT isnull(sum(b_value),0) num FROM retail_kudu.turnover where b_year = ? and b_quarter = ? ";

    //月
    String TOTAL_MONTH = "SELECT isnull(sum(b_value),0) num FROM retail_kudu.turnover where b_year = ? and b_month = ?";

    //周
    String TOTAL_WEEK = "SELECT isnull(sum(b_value),0) num FROM retail_kudu.turnover where b_year = ? and b_week = ? ";

    //日
    String TOTAL_DAY = "SELECT isnull(sum(b_value),0) num FROM retail_kudu.turnover where b_year = ? and b_month = ? and b_day = ? ";

    /**
     * 同比环比,由时间维度划
     */
    String RATE_BY_TIME = "SELECT CASE WHEN ta2.num =0 THEN -5 ELSE (ta1.num/ta2.num) END res FROM\n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) ta1,\n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) ta2";


    //年
    String RATE_YEAR = "SELECT CASE WHEN t2.num =0 THEN -5 ELSE (t1.num/t2.num) END res FROM\n" +
            "(SELECT isnull(sum(b_value),0) num FROM retail_kudu.turnover WHERE b_year = ? ) t1,\n" +
            "(SELECT isnull(sum(b_value),0) num FROM retail_kudu.turnover WHERE b_year = ? ) t2";

    //季度
    String RATE_QUARTER = "SELECT CASE WHEN t2.num =0 THEN -5 ELSE (t1.num/t2.num) END res FROM\n" +
            "(SELECT isnull(sum(b_value),0) FROM retail_kudu.turnover WHERE b_year = ? and b_quarter = ? ) t1,\n" +
            "(SELECT isnull(sum(b_value),0) FROM retail_kudu.turnover WHERE b_year = ? and b_quarter = ? ) t2";

    //月
    String RATE_MONTH = "SELECT CASE WHEN t2.num =0 THEN -5 ELSE (t1.num/t2.num) END res FROM\n" +
            "(SELECT isnull(sum(b_value),0) FROM retail_kudu.turnover WHERE b_year = ? and b_month = ? ) t1,\n" +
            "(SELECT isnull(sum(b_value),0) FROM retail_kudu.turnover WHERE b_year = ? and b_month = ? ) t2";

    //周
    String RATE_WEEK = "SELECT CASE WHEN t2.num =0 THEN -5 ELSE (t1.num/t2.num) END res FROM\n" +
            "(SELECT isnull(sum(b_value),0) FROM retail_kudu.turnover WHERE b_year = ? and b_week = ? ) t1,\n" +
            "(SELECT isnull(sum(b_value),0) FROM retail_kudu.turnover WHERE b_year = ? and b_week = ? ) t2";

    //日
    String RATE_DAY = "SELECT CASE WHEN t2.num =0 THEN -5 ELSE (t1.num/t2.num) END res FROM\n" +
            "(SELECT isnull(sum(b_value),0) FROM retail_kudu.turnover WHERE b_year = ? and b_month = ? and b_day = ? ) t1,\n" +
            "(SELECT isnull(sum(b_value),0) FROM retail_kudu.turnover WHERE b_year = ? and b_month = ? and b_day = ? ) t2";

}
