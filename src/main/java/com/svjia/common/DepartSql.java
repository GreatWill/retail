package com.svjia.common;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/12 09:26
 */
public interface DepartSql {

    /**
     * 总量,由时间维度划分
     */
    //所有
    String TOTAL_ALL = "select count(customerid) num from crs_kudu.customer where isdelete=0 and CPDeptLevelCode like '000003%'";


    //时间维度
    String TOTAL_BY_TIME = "select count(customerid) num from crs_kudu.customer where isdelete=0 and ( ? <= createtime and createtime <= ? ) and CPDeptLevelCode like '000003%'";

    /**
     * 同比环比,由时间维度划分
     */
    String RATE_BY_TIME = "SELECT CASE WHEN ta2.num =0 THEN -5 ELSE (ta1.num/ta2.num) END res FROM\n" +
            "(select count(customerid) num from crs_kudu.customer where isdelete=0 and ( ? <= createtime and createtime <= ? ) and CPDeptLevelCode like '000003%') ta1,\n" +
            "(select count(customerid) num from crs_kudu.customer where isdelete=0 and ( ? <= createtime and createtime <= ? ) and CPDeptLevelCode like '000003%') ta2";

}
