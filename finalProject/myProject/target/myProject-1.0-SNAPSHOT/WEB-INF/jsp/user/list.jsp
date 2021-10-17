<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User list</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">

</head>
<body>
    <%@include file="../header.jsp"%>
    <h1><b>Пользователи</b></h1>
    <c:url var="userDeleteUrl" value="/user/delete.html"/>
    <form action="${userDeleteUrl}" method="post">
        <ul>
            <c:forEach var="user" items="${users}">
            <c:url var="userEditUrl" value="/user/edit.html">
                <c:param name= "id" value="${user.id}"/>
            </c:url>
            <li><input type="checkbox" name="id" value="${user.id}">Логин: <a href="${userEditUrl}">${user.login}</a> [ Роль : ${user.role.name}]</li>
            </c:forEach>
        </ul>
        <c:url var="userEditUrl" value="/user/edit.html"/>
        <a style="text-decoration: none" href="${userEditUrl}">Добавить нового пользователя</a>
        <button class="form-control" style="width: 300px " type="submit">Удалить</button>
    </form>
</body>
</html>