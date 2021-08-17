package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.bean.User;
import com.hp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "UserUpdateServlet",urlPatterns = "/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.解决乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        //2.接收前端参数
        String username = req.getParameter("username");
        String real_name = req.getParameter("real_name");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        String is_del = req.getParameter("is_del");
        String modify_time = req.getParameter("modify_time");
        //缺少一个参数  即：主键id  修改是由主键id进行修改的
        String id = req.getParameter("id");

        //在修改之前，先查询出来前端没有的参数
        //调用service层
        UserService userService = new UserService();
        Map map = userService.selectUserById(Integer.parseInt(id));
        User data = (User) map.get("data");

        //把参数 赋值成对象
        User user = new User();
        user.setImg("???");
        user.setCreate_time("????");
        user.setUsername(username);
        user.setReal_name(real_name);
        user.setPassword(password);
        user.setType(Integer.parseInt(type));
        user.setIs_del(Integer.parseInt(is_del));
        user.setModify_time(modify_time);
        user.setId(Integer.parseInt(id));

        Map map1 = userService.updateUser(user);
        //4.把map 变成json
        String s = JSONObject.toJSONString(map1);
        System.out.println("s = " + s);
        //5.使用 流输出
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.close();
    }
}
