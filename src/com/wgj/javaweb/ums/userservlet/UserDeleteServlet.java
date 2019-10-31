package com.wgj.javaweb.ums.userservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UserDeleteServlet",urlPatterns = "/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String name = request.getParameter("name");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/javawebdb";
            String connectionUserName = "root";
            String connectionUserPassword = "root";
            Connection connection = DriverManager.getConnection(url,connectionUserName,connectionUserPassword);
            String sql = "delete from users where name=?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            int i = preparedStatement.executeUpdate();
            if (i==0){
                response.getWriter().println("用户不存在");
            }else {
                response.getWriter().println("删除成功");
            }
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
