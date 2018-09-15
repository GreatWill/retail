package com.svjia.common;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/12 09:26
 */
public interface OrderSql {

    /**
     * 总量,由时间维度划分
     */
    //所有
    String TOTAL_ALL = "String TOTAL_ALL = \"select count(distinct totalorderid) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 \\n\" +\n" +
            "            \"and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')\";";

    //时间维度
    String TOTAL_BY_TIME = "select count(distinct totalorderid) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%') ";

    /**
     * 同比环比
     */
    String RATE_BY_TIME = "SELECT CASE WHEN ta2.num =0 THEN -5 ELSE (ta1.num/ta2.num) END res FROM\n" +
            "(select count(distinct totalorderid) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and t1.paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%') ) ta1,\n" +
            "(select count(distinct totalorderid) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and t1.paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%') ) ta2";

}
