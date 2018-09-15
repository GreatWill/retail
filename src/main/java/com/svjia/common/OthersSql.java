package com.svjia.common;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/12 09:26
 */
public interface OthersSql {

    /**
     * 总量,由时间维度划分
     */
    //所有
    String TOTAL_ALL = "select count(distinct customerid) num from \n" +
            "(select clientuserid from sms_kudu.totalorder where isdelete=0 and totalordertype=10 ) t1,\n" +
            "(select distinct customerid,accountid from crs_kudu.customer where CPDeptLevelCode like '000003%' and shoptypeid not in ('Product','IntegratedWall')) t2\n" +
            "where t1.clientuserid = t2.accountid";


    //时间维度
    String TOTAL_BY_TIME = "select count(distinct customerid) num from \n" +
            "(select clientuserid from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )) t1,\n" +
            "(select distinct customerid,accountid from crs_kudu.customer where CPDeptLevelCode like '000003%' and shoptypeid not in ('Product','IntegratedWall')) t2\n" +
            "where t1.clientuserid = t2.accountid";

    /**
     * 同比环比,由时间维度划分
     */
    String RATE_BY_TIME = "SELECT CASE WHEN ta2.num =0 THEN -5 ELSE (ta1.num/ta2.num) END res FROM\n" +
            "(select count(distinct customerid) num from \n" +
            "(select clientuserid from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )) t1,\n" +
            "(select distinct customerid,accountid from crs_kudu.customer where CPDeptLevelCode like '000003%' and shoptypeid not in ('Product','IntegratedWall')) t2\n" +
            "where t1.clientuserid = t2.accountid) ta1,\n" +
            "(select count(distinct customerid) num from \n" +
            "(select clientuserid from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )) t1,\n" +
            "(select distinct customerid,accountid from crs_kudu.customer where CPDeptLevelCode like '000003%' and shoptypeid not in ('Product','IntegratedWall')) t2\n" +
            "where t1.clientuserid = t2.accountid) ta2";

}
