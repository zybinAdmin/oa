<%@ page import="com.zhang.oa.bean.Dept" %><%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/8
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<html>
<head>
    <title>修改部门</title>
</head>
<body>
<h1>修改部门</h1>
<hr>
<%
    Dept dept = (Dept) request.getAttribute("dept");
%>
<form method="post" action="<%=request.getContextPath()%>/dept/update">
    部门编号<input type="text" name="deptno" value="<%=dept.getDeptno()%>" readonly><br>
    部门名称<input type="text" name="dname" value="<%=dept.getDname()%>"><br>
    部门位置<input type="text" name="loc" value="<%=dept.getLoc()%>"><br>
    <input type="submit" value="修改"><br>
</form>
</body>
</html>
