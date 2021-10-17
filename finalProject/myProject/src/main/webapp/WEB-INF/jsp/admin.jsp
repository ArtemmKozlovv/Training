<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Админ панель</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div>
            <c:url var="urlUserList" value="/user/list.html"/>
            <a href="${urlUserList}"><button type="button" style="margin-top: 10px;margin-bottom: 10px;" class="w-100 btn btn-lg btn-primary">Пользователи</button></a>
        </div>
        <div class="text-center">
            <c:url var="urlTourList" value="/tour/list.html"/>
            <a href="${urlTourList}"><button style="margin-top: 10px;margin-bottom: 10px;" class="w-100 btn btn-lg btn-primary">Туры</button></a>
        </div>
        <div class="text-center">
            <c:url var="urlRequestList" value="/request/list.html"/>
            <a href="${urlRequestList}"><button style="margin-top: 10px;margin-bottom: 10px;" class="w-100 btn btn-lg btn-primary">Запросы</button></a>
        </div>
    </body>
</html>