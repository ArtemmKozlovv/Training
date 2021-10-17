<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Профиль</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">

    </head>
    <body>
        <%@include file="../header.jsp"%>
            <main class="form-signin">
                <h1 class="text-center">Ваш логин: ${user.login}</h1>
                <c:url var="userSaveUrl" value="/user/saveProfile.html"/>
                <form action="${userSaveUrl}" method="post" style="margin: 0 auto; width: 300px; height: 410px; display: block; bottom: 100px;">
                    <c:if test="${not empty param.message}">
                        <p style="color: #af0a0a;">${param.message}</p>
                    </c:if>
                    <c:if test="${not empty user}">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="login" value="${user.login}">
                        <input type="hidden" name="password" value="${user.password}">
                        <input type="hidden" name="role" value="${user.role}">
                        <input type="hidden" name="count_of_tours" value="${user.count_of_tours}">
                        <input type="hidden" name="id_tour" value="${user.id_tour}">
                    </c:if>
                    <div class="form-floating">
                        <h4><b>Имя:</b></h4>
                        <input type="text" class="form-control" placeholder="Name" name="name" value="${user.name}">
                    </div>
                    <div class="form-floating">
                        <br>
                        <h4><b>Фамилия:</b></h4>
                        <input type="text" class="form-control" placeholder="Surname" name="surname" value="${user.surname}">
                    </div>
                    <button class="w-100 btn btn-lg btn-primary" type="submit">Изменить</button>
                </form>
            </main>
        <div class="text-center">
            <c:url var="urlFavoriteTour" value="/user/selectTour.html"/>
            <a href="${urlFavoriteTour}"><button type="button" style="margin-top: 10px;margin-bottom: 10px;" class="w-100 btn btn-lg btn-primary">Выбранный вами тур</button></a>
        </div>
        <c:if test="${user.role.name != 'Пользователь'}">
            <div class="text-center">
                <c:url var="urlAdmin" value="/admin.html"/>
                <a href="${urlAdmin}"><button type="button" style="margin-top: 10px;margin-bottom: 10px;" class="w-100 btn btn-lg btn-primary">Админ панель</button></a>
            </div>
        </c:if>
    </body>
</html>