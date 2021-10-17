<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Авторизация</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    </head>
    <body class="text-center">
    <%@include file="header.jsp"%>
    <h1 class="h3 mb-3 fw-normal">Авторизация</h1>
    <main class="form-signin">
        <c:url var="loginUrl" value="/login.html"/>
        <form action="${loginUrl}" method="post" style="margin: 0 auto; width: 300px; height: 410px; display: block; bottom: 100px;">
            <c:if test="${not empty param.message}">
                <p style="color: #af0a0a;">${param.message}</p>
            </c:if>
            <c:url var="loginUrl" value="/login.html"/>
            <c:if test="${not empty user}">
                <input type="hidden" name="id" value="${user.id}">
            </c:if>
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInput" placeholder="Login" name="login">
                <label for="floatingInput">Login</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
                <label for="floatingPassword">Password</label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Войти</button>
            <p class="mt-5 mb-3 text-muted">© Kozlov Artem 2021</p>
        </form>
    </main>
    </body>
</html>