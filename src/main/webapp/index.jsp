<%--
  Created by IntelliJ IDEA.
  User: zhyantai
  Date: 2017/9/11
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<%--<jsp:forward page="WEB-INF/jsps/course_overview.jsp"></jsp:forward>--%>

<form name="dd" method="post" action="/hello/viewAll">

    username:<input type="text" name="name"/>
    <br>
    <br>
    password:<input type="password" name="password"/>
    <br>
    <br>
    <input type="submit" >


</form>


</body>
</html>
