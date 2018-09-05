package com.svjia.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * IMPALA连接类
 */
public class ImpalaConnnection {

    private Log logger = LogFactory.getLog(ImpalaConnnection.class);

    /**
     * 获取连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
       Class.forName(Constants.JDBC_DRIVER_NAME);
       Connection connection = DriverManager.getConnection(Constants.CONNECTION_URL);
       return connection;
    }

    /**
     * 关闭连接
     * @param connection
     * @param stmt
     * @param rs
     * @throws Exception
     */
    public static void close(Connection connection,Statement stmt,ResultSet rs) throws Exception {

        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (connection != null) {
            connection.close();
        }

    }

}