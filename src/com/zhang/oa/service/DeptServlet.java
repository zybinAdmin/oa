package com.zhang.oa.service;

import com.zhang.oa.bean.Dept;
import com.zhang.oa.utils.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/dept/list","/dept/add","/dept/detail","/dept/edit","/dept/del","/dept/update"})
public class DeptServlet extends HttpServlet {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            //拿到项目的根路径
            String servletPath = request.getServletPath();
            //判断路径属于哪个servlet，执行那个方法
            if("/dept/list".equals(servletPath)) {
                doList(request, response);
            } else if("/dept/add".equals(servletPath)) {
                doAdd(request, response);
            } else if("/dept/detail".equals(servletPath)) {
                doDetail(request, response);
            } else if("/dept/edit".equals(servletPath)) {
                doEdit(request, response);
            } else if("/dept/del".equals(servletPath)) {
                doDel(request, response);
            } else if("/dept/update".equals(servletPath)) {
                doUpdate(request, response);
            }
        } else{
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    /**
     * 修改部门信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        request.setCharacterEncoding("gbk");
        //获取前端发送的参数
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update dept set dname = ?,loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setString(3,deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtil.close(conn,ps,null);
        }
        response.sendRedirect(request.getContextPath() + "/dept/list");
    }

    /**
     * 删除部门
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        String deptno = request.getParameter("deptno");
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from dept where deptno = ?";
            ps =  conn.prepareStatement(sql);
            ps.setString(1,deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            //释放资源
            JDBCUtil.close(conn,ps,null);
        }
        //判断是否删除成功
        if(count == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else{
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }

    }

    /**
     * 显示修改的部门信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        String deptnos = request.getParameter("deptno");
        Dept dept = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select deptno,dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptnos);
            rs = ps.executeQuery();
            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                dept = new Dept(deptno, dname, loc);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtil.close(conn,ps,rs);
        }

        request.setAttribute("dept",dept);
        request.getRequestDispatcher("/edit.jsp").forward(request,response);
    }



    /**
     * 查询部门详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        Dept dept = null;
        String deptnos = request.getParameter("deptno");
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select deptno,dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptnos);
            rs = ps.executeQuery();
            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                dept = new Dept(deptno, dname, loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtil.close(conn,ps,rs);
        }
        request.setAttribute("depts",dept);
        request.getRequestDispatcher("/detail.jsp").forward(request,response);
    }

    /**
     * 添加部门信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //request.setCharacterEncoding("gbk");

        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtil.close(conn,ps,null);
        }
        if(count == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else{
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }

    }

    /**
     * 查询所有的部门信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        List<Dept> depts = new ArrayList();

        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            //执行sql语句
            String sql = "select deptno,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept(deptno,dname,loc);
                depts.add(dept);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            JDBCUtil.close(conn,ps,rs);
        }

        request.setAttribute("dept",depts);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
