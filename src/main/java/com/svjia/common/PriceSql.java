package com.svjia.common;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/12 09:26
 */
public interface PriceSql {

    /**
     * 总量,由时间维度划分
     */
    //所有
    String TOTAL_ALL = "select case when t2.num =0 then -5 else (t1.num / t2.num) end num from\n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 \n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) t1,\n" +
            "(select count(distinct totalorderid) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 \n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) t2";


    String TOTAL_BY_TIME = "select case when t2.num =0 then -5 else (t1.num / t2.num) end num from\n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) t1,\n" +
            "(select count(distinct totalorderid) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%') ) t2";


    /**
     * 同比环比,由时间维度划分
     */
    String RATE_BY_TIME = "SELECT CASE WHEN ta2.num =0 THEN -5 ELSE (ta1.num/ta2.num) END res FROM\n" +
            "(select case when t2.num =0 then -5 else (t1.num / t2.num) end num from\n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) t1,\n" +
            "(select count(distinct totalorderid) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%') ) t2) ta1,\n" +
            "(select case when t2.num =0 then -5 else (t1.num / t2.num) end num from\n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) t1,\n" +
            "(select count(distinct totalorderid) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%') ) t2) ta2";
}
