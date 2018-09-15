package com.svjia.common;

/**
 * @Description:
 * @Auther: chenjw
 * @Date: 2018/9/12 09:26
 */
public interface AvgoutSql {

    /**
     * 总量,由时间维度划分
     */
    //所有
    String TOTAL_ALL = "select CASE WHEN rr2.num = 0 THEN -5 ELSE (rr1.num/rr2.num) END num from \n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 \n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) rr1,\n" +
            "(select (r1.num - r2.num) num from \n" +
            "(select count(distinct t1.id) num from foxone_kudu.sys_user t1, foxone_kudu.sys_department t2 where t1.departmentId = t2.id and t2.WBS like '001004%' ) r1,\n" +
            "(select count(distinct a.id) num from foxone_kudu.sys_user a inner join foxone_kudu.sys_userrole b on a.id = b.userid \n" +
            "inner join foxone_kudu.sys_role c on b.roleid = c.id inner join foxone_kudu.sys_roletype d on c.roletypeid = d.id \n" +
            "inner join foxone_kudu.sys_department e on e.id = a.departmentId where d.Name = '总监' and e.WBS like '001004%' ) r2) rr2";


    //时间维度
    String TOTAL_BY_TIME = "select CASE WHEN rr2.num = 0 THEN -5 ELSE (rr1.num/rr2.num) END num from \n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( '2018-02-12' <= paytime and paytime <= '2018-03-12' )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) rr1,\n" +
            "(select (r1.num - r2.num) num from \n" +
            "(select count(distinct t1.id) num from foxone_kudu.sys_user t1, foxone_kudu.sys_department t2 where t1.departmentId = t2.id and t2.WBS like '001004%'\n" +
            "and t1.id not in (select distinct type from foxone_kudu.sys_durpproperty where (name = '_ExtField_UserCreateTime' and to_timestamp(value, 'yyyy/M/d') >= to_timestamp( ?, 'yyyy-MM-dd') ) \n" +
            "or (name = '_ExtField_UserLeaveTime' and to_timestamp(value, 'yyyy/M/d') <= to_timestamp( ?, 'yyyy-MM-dd') ) )) r1,\n" +
            "(select count(distinct a.id) num from foxone_kudu.sys_user a inner join foxone_kudu.sys_userrole b on a.id = b.userid \n" +
            "inner join foxone_kudu.sys_role c on b.roleid = c.id inner join foxone_kudu.sys_roletype d on c.roletypeid = d.id \n" +
            "inner join foxone_kudu.sys_department e on e.id = a.departmentId where d.Name = '总监' and e.WBS like '001004%' ) r2) rr2";

    /**
     * 同比环比,由时间维度划分
     */
    String RATE_BY_TIME = "SELECT CASE WHEN ta2.num =0 THEN -5 ELSE (ta1.num/ta2.num) END res FROM\n" +
            "(select CASE WHEN rr2.num = 0 THEN -5 ELSE (rr1.num/rr2.num) END num from \n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( ? <= paytime and paytime <= ? )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) rr1,\n" +
            "(select (r1.num - r2.num) num from \n" +
            "(select count(distinct t1.id) num from foxone_kudu.sys_user t1, foxone_kudu.sys_department t2 where t1.departmentId = t2.id and t2.WBS like '001004%'\n" +
            "and t1.id not in (select distinct type from foxone_kudu.sys_durpproperty where (name = '_ExtField_UserCreateTime' and to_timestamp(value, 'yyyy/M/d') >= to_timestamp( ?, 'yyyy-MM-dd HH:mm:ss') ) \n" +
            "or (name = '_ExtField_UserLeaveTime' and to_timestamp(value, 'yyyy/M/d') <= to_timestamp( ?, 'yyyy-MM-dd HH:mm:ss') ) )) r1,\n" +
            "(select count(distinct a.id) num from foxone_kudu.sys_user a inner join foxone_kudu.sys_userrole b on a.id = b.userid \n" +
            "inner join foxone_kudu.sys_role c on b.roleid = c.id inner join foxone_kudu.sys_roletype d on c.roletypeid = d.id \n" +
            "inner join foxone_kudu.sys_department e on e.id = a.departmentId where d.Name = '总监' and e.WBS like '001004%' ) r2) rr2) ta1,\n" +
            "(select CASE WHEN rr2.num = 0 THEN -5 ELSE (rr1.num/rr2.num) END num from \n" +
            "(select sum(paymoney) num from sms_kudu.totalorder where isdelete=0 and totalordertype=10 and ( '2018-02-12' <= paytime and paytime <= '2018-03-12' )\n" +
            "and clientuserid in (select distinct accountid from crs_kudu.customer where CPDeptLevelCode like '000003%')) rr1,\n" +
            "(select (r1.num - r2.num) num from \n" +
            "(select count(distinct t1.id) num from foxone_kudu.sys_user t1, foxone_kudu.sys_department t2 where t1.departmentId = t2.id and t2.WBS like '001004%'\n" +
            "and t1.id not in (select distinct type from foxone_kudu.sys_durpproperty where (name = '_ExtField_UserCreateTime' and to_timestamp(value, 'yyyy/M/d') >= to_timestamp( ?, 'yyyy-MM-dd HH:mm:ss') ) \n" +
            "or (name = '_ExtField_UserLeaveTime' and to_timestamp(value, 'yyyy/M/d') <= to_timestamp( ?, 'yyyy-MM-dd HH:mm:ss') ) )) r1,\n" +
            "(select count(distinct a.id) num from foxone_kudu.sys_user a inner join foxone_kudu.sys_userrole b on a.id = b.userid \n" +
            "inner join foxone_kudu.sys_role c on b.roleid = c.id inner join foxone_kudu.sys_roletype d on c.roletypeid = d.id \n" +
            "inner join foxone_kudu.sys_department e on e.id = a.departmentId where d.Name = '总监' and e.WBS like '001004%' ) r2) rr2) ta2";

}
