package com.wgj.javaweb.ums.userservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UserUpdateServlet",urlPatterns = "/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        Integer age = Integer.parseInt(request.getParameter("password"));
        String detestr = request.getParameter("birthday");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //存入全局的user变量的ArrayList列表中
        ////获取全局servletContext对象的user变量
        //如果列表中，用户已经存在(判定规则:用户名)，返回注册失败,
        try {
            Date birthday = format.parse(detestr);
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/javawebdb";
            String connectionUserName = "root";
            String connectionUserPassword = "root";
            Connection connection = DriverManager.getConnection(url,connectionUserName,connectionUserPassword);
            String sql = "update users set password=?,sex=?,age=?,birthday=? where name=?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,sex);
            preparedStatement.setInt(3,age);
            preparedStatement.setDate(4,new java.sql.Date(birthday.getTime()));
            preparedStatement.setString(5,name);
            int i = preparedStatement.executeUpdate();
            if (i==0){
                response.getWriter().println("用户不存在");
            }else {
                response.getWriter().println("更新成功");
            }
            preparedStatement.close();
            connection.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
