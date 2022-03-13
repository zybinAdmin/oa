<%@ page import="com.zhang.oa.bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/8
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门详情</title>
</head>
<body>
<h1>部门详情</h1>
<%
    Dept depts = (Dept) request.getAttribute("depts");
%>
<hr>
部门编号：<%=depts.getDeptno()%><br>
部门名称：<%=depts.getDname()%><br>
部门位置：<%=depts.getLoc()%><br>
<input type="button" value="后退" onclick="window.history.back()">
</body>
</html>
