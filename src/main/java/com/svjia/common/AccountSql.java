package com.svjia.common;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/12 09:26
 */
public interface AccountSql {

    /**
     * 总量,由时间维度划分
     */
    //所有
    String TOTAL_ALL = "select count(*) result from \n" +
            "(select a.userid userid from \n" +
            "syscore_kudu.users a \n" +
            "left join sms_kudu.totalorder b \n" +
            "on a.userid = b.clientuserid \n" +
            "left join crs_kudu.customer c \n" +
            "on c.accountid = b.clientuserid \n" +
            "left join syscore_kudu.employee d \n" +
            "on a.userid = d.userid \n" +
            "left join syscore_kudu.department e \n" +
            "on d.deptid = e.deptid \n" +
            "where a.isdelete='0' \n" +
            "and b.isdelete=0 \n" +
            "and b.totalordertype=10 \n" +
            "and c.CPDeptLevelCode like '000003%' \n" +
            ")as r";


    //时间维度
    String TOTAL_BY_TIME = "select count(*) result from \n" +
            "(select a.userid userid from \n" +
            "syscore_kudu.users a \n" +
            "left join sms_kudu.totalorder b \n" +
            "on a.userid = b.clientuserid \n" +
            "left join crs_kudu.customer c \n" +
            "on c.accountid = b.clientuserid \n" +
            "left join syscore_kudu.employee d \n" +
            "on a.userid = d.userid \n" +
            "left join syscore_kudu.department e \n" +
            "on d.deptid = e.deptid \n" +
            "where a.isdelete='0' \n" +
            "and b.isdelete=0 \n" +
            "and b.totalordertype=10 \n" +
            "and ( ? <= b.paytime and  b.paytime <= ?) \n" +
            "and c.CPDeptLevelCode like '000003%' \n" +
            ")as r where r.userid not in (select distinct userid from syscore_kudu.users a \n" +
            "left join sms_kudu.totalorder b on a.userid = b.clientuserid where paytime <= ?\n" +
            ")";

    /**
     * 同比环比,由时间维度划分
     */
    String RATE_BY_TIME = "SELECT CASE WHEN ta2.num =0 THEN -5 ELSE (ta1.num/ta2.num) END res FROM\n" +
            "(select count(*) result from \n" +
            "(select a.userid userid from \n" +
            "syscore_kudu.users a \n" +
            "left join sms_kudu.totalorder b \n" +
            "on a.userid = b.clientuserid \n" +
            "left join crs_kudu.customer c \n" +
            "on c.accountid = b.clientuserid \n" +
            "left join syscore_kudu.employee d \n" +
            "on a.userid = d.userid \n" +
            "left join syscore_kudu.department e \n" +
            "on d.deptid = e.deptid \n" +
            "where a.isdelete='0' \n" +
            "and b.isdelete=0 \n" +
            "and b.totalordertype=10 \n" +
            "and ( ? <= b.paytime and  b.paytime <= ?) \n" +
            "and c.CPDeptLevelCode like '000003%' \n" +
            ")as r where r.userid not in (select distinct userid from syscore_kudu.users a \n" +
            "left join sms_kudu.totalorder b on a.userid = b.clientuserid where paytime <= ?\n" +
            ")) ta1,\n" +
            "(select count(*) result from \n" +
            "(select a.userid userid from \n" +
            "syscore_kudu.users a \n" +
            "left join sms_kudu.totalorder b \n" +
            "on a.userid = b.clientuserid \n" +
            "left join crs_kudu.customer c \n" +
            "on c.accountid = b.clientuserid \n" +
            "left join syscore_kudu.employee d \n" +
            "on a.userid = d.userid \n" +
            "left join syscore_kudu.department e \n" +
            "on d.deptid = e.deptid \n" +
            "where a.isdelete='0' \n" +
            "and b.isdelete=0 \n" +
            "and b.totalordertype=10 \n" +
            "and ( ? <= b.paytime and  b.paytime <= ?) \n" +
            "and c.CPDeptLevelCode like '000003%' \n" +
            ")as r where r.userid not in (select distinct userid from syscore_kudu.users a \n" +
            "left join sms_kudu.totalorder b on a.userid = b.clientuserid where paytime <= ?\n" +
            ")) ta2";

}
