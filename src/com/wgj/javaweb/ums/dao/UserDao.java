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
                JDBCUtils.release(preparedStatement, connection, rs);
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
            return false;
        }
    }

    public boolean delete(String name) throws SQLException, ClassNotFoundException {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "delete from users where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                JDBCUtils.release(preparedStatement, connection);
                return false;
            } else {
                JDBCUtils.release(preparedStatement, connection);
                return false;
            }
        } finally {
            return false;
        }
    }

    public User getOne(){
        return null;
    }

    public ArrayList<User> getList(){
        //到全局变量列表中去比对，比对的原则：用户名和密码都要相同，否则返回用户名或密码错误信息
        ////获取全局servletContext对象的user变量
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from users";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            final ResultSet rs = preparedStatement.executeQuery(sql);
            //操作结果集
            ArrayList<User> result = new ArrayList<>();
            while (rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                Integer age = rs.getInt("age");
                Date birthday = rs.getDate("birthday");
                System.out.println(id+name);
                User user = new User(id,name,null,sex,age,birthday);
                result.add(user);
            }
            JDBCUtils.release(preparedStatement, connection, rs);
            return result;

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
