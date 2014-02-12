<%--
  Created by IntelliJ IDEA.
  User: Jeremie
  Date: 14-2-12
  Time: 下午8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
    <title></title>
</head>
<body>
    <sf:form id="inputForm" cssClass="form-horizontal" method="post" action="login.html" modelAttribute="user">
        账号：<input name="name" id="name" maxlength="100" /> <br/>
        密码：<input name="password" id="password" type="password" maxlength="100" /> <br/>
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="登陆"/>
    </sf:form>
</body>
</html>
