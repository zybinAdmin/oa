package com.zhang.oa.service;

import com.zhang.oa.utils.JDBCUtil;
import sun.plugin.dom.core.Element;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/user/exit".equals(servletPath)) {
            doExit(request, response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        if(session != null && cookies != null) {
            //手动销毁session对象
            session.invalidate();

            //销毁cookie对象
            Cookie cookie1 = new Cookie("username",null);
            Cookie cookie2 = new Cookie("password", null);
            //设置path
            cookie1.setPath(request.getContextPath());
            cookie2.setPath(request.getContextPath());
            //删除cookie
            cookie1.setMaxAge(0);
            cookie2.setMaxAge(0);
            //响应给浏览器
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.sendRedirect(request.getContextPath());
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean sccess =false;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id,username,password from t_user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()){
                sccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,ps,rs);
        }

        if (sccess) {
            HttpSession session = request.getSession();
            session.setAttribute("username",username);

            //十天内免登录
            String f = request.getParameter("f");
            //判断用户是否勾选了10天内免登录
            if("1".equals(f)){
                //用户选择了十天免登录设置session的过期时间为十天
                //没有选择十天免登录的用户session的有效时间为1分钟
                //以秒为单位
                session.setMaxInactiveInterval(60*60*24*10);

                //创建两个Cookie对象存放用户名和密码
                Cookie cookie1 = new Cookie("username",username);
                Cookie cookie2 = new Cookie("password", password);
                //设置Cookie的存活时间为10天
                cookie1.setMaxAge(60*60*24*10);
                cookie2.setMaxAge(60*60*24*10);
                //设置Cookie的路径
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                //将Cookie响应给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }

            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
