package com.zhang.oa.service;

import com.zhang.oa.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取所有的cookie
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("username".equals(name)){
                    username = cookie.getValue();
                } else if("password".equals(name)){
                    password = cookie.getValue();
                }
            }
        }

        if (username != null && password != null){
            boolean sccess =false;
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
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
                //登录成功
                //获取session
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect(request.getContextPath()+"/dept/list");
            } else {
                //登录失败
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }

        } else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}
