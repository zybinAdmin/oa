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
    <title>�޸Ĳ���</title>
</head>
<body>
<h1>�޸Ĳ���</h1>
<hr>
<%
    Dept dept = (Dept) request.getAttribute("dept");
%>
<form method="post" action="<%=request.getContextPath()%>/dept/update">
    ���ű��<input type="text" name="deptno" value="<%=dept.getDeptno()%>" readonly><br>
    ��������<input type="text" name="dname" value="<%=dept.getDname()%>"><br>
    ����λ��<input type="text" name="loc" value="<%=dept.getLoc()%>"><br>
    <input type="submit" value="�޸�"><br>
</form>
</body>
</html>
