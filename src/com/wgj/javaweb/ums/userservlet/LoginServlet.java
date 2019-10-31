package com.wgj.javaweb.ums.userservlet;

import com.wgj.javaweb.ums.dao.UserDao;

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
        UserDao userDao = new UserDao();
        boolean rs = userDao.login(name,password);
        if (rs){
            response.sendRedirect("main.html");
        }else {
            response.getWriter().println("用户名或密码错误");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
