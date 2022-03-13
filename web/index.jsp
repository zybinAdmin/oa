<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/6
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
  <h1>LOGIN PAGE</h1>
  <hr>
  <form method="post" action="<%=request.getContextPath()%>/user/login">
    username:<input type="text" name="username"/><br>
    password:<input type="password" name="password"/><br>
    <input type="checkbox" name="f" value="1"/>十天内免登录<br>
    <input type="submit" value="login"/>
  </form>

<%--  <a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a>--%>

  </body>
</html>
