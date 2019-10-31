package com.wgj.javaweb.ums.userservlet;

import com.wgj.javaweb.ums.dao.UserDao;
import com.wgj.javaweb.ums.entity.User;

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

        try {
            Date birthday = format.parse(detestr);
            UserDao userDao = new UserDao();
            User user = new User(name,password,sex,age,birthday);
            boolean rs = userDao.update(user);
            if (rs){
                response.getWriter().println("更新成功");
            }else {
                response.getWriter().println("用户不存在");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
