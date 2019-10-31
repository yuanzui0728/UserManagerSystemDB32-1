package com.wgj.javaweb.ums.dao;

import com.wgj.javaweb.ums.entity.User;
import com.wgj.javaweb.ums.utils.JDBCUtils;

import javax.swing.text.html.HTMLEditorKit;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserDao {

    public boolean insert(User user) throws SQLException, ClassNotFoundException, ParseException {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from users where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            final ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                String sql1 = "insert into users (name,password,sex,age,birthday) values(?,?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(sql1);
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getSex());
                pstmt.setInt(4, user.getAge());
                pstmt.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
                pstmt.executeLargeUpdate();
                JDBCUtils.release(preparedStatement, connection, rs);
                return true;
            }
        } finally {

        }
    }

    public boolean delete(){
        return false;
    }

    public User getOne(){
        return null;
    }

    public ArrayList getList(){
        return null;
    }

}
