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
    <title>��������</title>
</head>
<body>
<h1>��������</h1>
<hr>
<form method="get" action="<%=request.getContextPath()%>/dept/add">
    ���ű��<input type="text" name="deptno"><br>
    ��������<input type="text" name="dname"><br>
    ����λ��<input type="text" name="loc"><br>
    <input type="submit" value="����"><br>
</form>
</body>
</html>
