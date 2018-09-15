package com.svjia.common;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/12 09:26
 */
public interface IntentionSql {

    /**
     * 总量,由时间维度划分
     */
    //所有
    String TOTAL_ALL = "select count(distinct customerid) num from crs_kudu.customerstatusrelation where isdelete=0 and customerstatus in ('Connected','Shown','Forcing')";


    //时间维度
    String TOTAL_BY_TIME = "select count(distinct customerid) num from crs_kudu.customerstatusrelation where isdelete=0 and ( ? <= createtime and createtime <= ?) \n" +
            "and customerstatus in ('Connected','Shown','Forcing')";

    /**
     * 同比环比,由时间维度划分
     */
    String RATE_BY_TIME = "SELECT CASE WHEN ta2.num =0 THEN -5 ELSE (ta1.num/ta2.num) END res FROM\n" +
            "(select count(distinct customerid) num from crs_kudu.customerstatusrelation where isdelete=0 and ( ? <= createtime and createtime <= ?) \n" +
            "and customerstatus in ('Connected','Shown','Forcing')) ta1,\n" +
            "(select count(distinct customerid) num from crs_kudu.customerstatusrelation where isdelete=0 and ( ? <= createtime and createtime <= ?) \n" +
            "and customerstatus in ('Connected','Shown','Forcing')) ta2";

}
