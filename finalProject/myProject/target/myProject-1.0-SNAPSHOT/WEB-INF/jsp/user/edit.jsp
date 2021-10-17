<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Редактирование пользователя ${user.login}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового пользователя"/>
    </c:otherwise>
</c:choose>

<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    </head>
    <body>
    <h1><b>${title}</b></h1>
    <c:url var="userSaveUrl" value="/user/save.html"/>
    <form action="${userSaveUrl}" method="post" style="border: 4px double black; width: 200px;">
        <c:if test="${not empty user}">
            <input type="hidden" name="id" value="${user.id}">
        </c:if>
        <div class="form-floating">
            <h4><b>Имя :</b></h4>
            <input type="text" class="form-control" placeholder="Name" name="name" value="${user.name}">
        </div>
        <div class="form-floating">
            <br>
            <h4><b>Фамилия :</b></h4>
            <input type="text" class="form-control"  placeholder="Surname" name="surname" value="${user.surname}">
        </div>
        <div class="form-floating">
            <br>
            <h4><b>Логин :</b></h4>
            <input type="text" class="form-control" placeholder="Login" name="login" value="${user.login}">
        </div>
        <div class="form-floating">
            <br>
            <h4><b>Пороль :</b></h4>
            <input type="text" class="form-control"  placeholder="Password" name="password" value="${user.password}">
        </div>
        <div class="form-floating">
            <br>
            <h4><b>Колличество туров :</b></h4>
            <input type="text" class="form-control"  placeholder="Count_of_tours" name="count_of_tours" value="${user.count_of_tours}">
        </div>
        Роль<br>
        <select name="role">
            <c:forEach var="role" items="${roles}">
                <c:choose>
                    <c:when test="${role == user.role}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${role}" ${selected}>${role.name}</option>
            </c:forEach>
        </select>
        <br>
        <br>
    <button type="submit">Сохранить</button>
    </form>
    </body>
</html>