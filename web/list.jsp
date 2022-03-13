<%@ page import="com.zhang.oa.bean.Dept" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/8
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表页面</title>
</head>
<script type="text/javascript">
    function del(deptno) {
        var ok = window.confirm("确认要删除吗？");
        if (ok){
            document.location.href = "<%=request.getContextPath()%>/dept/del?deptno="+deptno;
        }
    }
</script>
<body>
<h1>部门列表页面</h1>
<h3>欢迎[<%=session.getAttribute("username")%>]</h3>
<a href="<%=request.getContextPath()%>/user/exit">[退出系统]</a>
<hr>
<table border="1" align="center" width="50%">
    <tr>
        <td>序号</td>
        <td>部门编号</td>
        <td>部门名称</td>
        <td>操作</td>
    </tr>
    <%
        int i = 0;
        List<Dept> deptList = (List<Dept>) request.getAttribute("dept");

        for (Dept dept : deptList){
    %>
    <tr>
        <td><%=++i%></td>
        <td><%=dept.getDeptno()%></td>
        <td><%=dept.getDname()%></td>
        <td>
            <a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
            <a href="<%=request.getContextPath()%>/dept/edit?deptno=<%=dept.getDeptno()%>">修改</a>
            <a href="<%=request.getContextPath()%>/dept/detail?deptno=<%=dept.getDeptno()%>">详情</a>
        </td>
    </tr>
    <%
        }
    %>

</table>
<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
</body>
</html>
