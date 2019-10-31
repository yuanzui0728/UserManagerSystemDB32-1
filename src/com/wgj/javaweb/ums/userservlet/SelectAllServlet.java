package com.wgj.javaweb.ums.userservlet;

import com.alibaba.fastjson.JSON;
import com.wgj.javaweb.ums.dao.UserDao;
import com.wgj.javaweb.ums.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SelectAllServlet",urlPatterns = "/SelectAllServlet")
public class SelectAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        ArrayList<User> result =userDao.getList();
        String s = JSON.toJSONString(result);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().println(s);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
