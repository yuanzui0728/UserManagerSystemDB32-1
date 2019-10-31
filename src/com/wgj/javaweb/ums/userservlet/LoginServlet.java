package com.wgj.javaweb.ums.userservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收请求的参数数据
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //到全局变量列表中去比对，比对的原则：用户名和密码都要相同，否则返回用户名或密码错误信息
        ////获取全局servletContext对象的user变量
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/javawebdb";
            String connectionUserName = "root";
            String connectionUserPassword = "root";
            Connection connection = DriverManager.getConnection(url,connectionUserName,connectionUserPassword);
            String sql = "select * from users where name=? and password=?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            final ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                response.sendRedirect("main.html");
            }else {
                response.getWriter().println("用户名或密码错误");
            }
            rs.close();
            preparedStatement.close();
            connection.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        ////否则，返回用户名或密码错误信息
        response.getWriter().println("用户名或者密码不正确");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
