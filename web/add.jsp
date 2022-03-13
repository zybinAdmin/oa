<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/8
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<html>
<head>
    <title>新增部门</title>
</head>
<body>
<h1>新增部门</h1>
<hr>
<form method="get" action="<%=request.getContextPath()%>/dept/add">
    部门编号<input type="text" name="deptno"><br>
    部门名称<input type="text" name="dname"><br>
    部门位置<input type="text" name="loc"><br>
    <input type="submit" value="保存"><br>
</form>
</body>
</html>
