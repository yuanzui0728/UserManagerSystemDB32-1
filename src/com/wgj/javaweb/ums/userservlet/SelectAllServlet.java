package com.wgj.javaweb.ums.userservlet;

import com.alibaba.fastjson.JSON;
import com.wgj.javaweb.ums.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "SelectAllServlet",urlPatterns = "/SelectAllServlet")
public class SelectAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //到全局变量列表中去比对，比对的原则：用户名和密码都要相同，否则返回用户名或密码错误信息
        ////获取全局servletContext对象的user变量
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/javawebdb";
            String connectionUserName = "root";
            String connectionUserPassword = "root";
            Connection connection = DriverManager.getConnection(url,connectionUserName,connectionUserPassword);
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
            String s = JSON.toJSONString(result);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().println(s);
            //关闭连接
            rs.close();
            preparedStatement.close();
            connection.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
