package com.wgj.javaweb.ums.utils;

import java.sql.*;

public class JDBCUtils {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    //连接
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/javawebdb";
        String connectionUserName = "root";
        String connectionUserPassword = "root";
        Connection connection = DriverManager.getConnection(url,connectionUserName,connectionUserPassword);
        return connection;
    }

    //释放
    public static void release(PreparedStatement preparedStatement,Connection connection,ResultSet resultSet) throws SQLException {
        if (resultSet!=null){
            resultSet.close();
            resultSet = null;
        }
        if (preparedStatement!=null){
            preparedStatement.close();
            preparedStatement = null;
        }
        if (connection!=null){
            connection.close();
            connection = null;
        }
    }

    //释放
    public static void release(PreparedStatement preparedStatement,Connection connection) throws SQLException {
        if (preparedStatement!=null){
            preparedStatement.close();
            preparedStatement = null;
        }
        if (connection!=null){
            connection.close();
            connection = null;
        }
    }

}
