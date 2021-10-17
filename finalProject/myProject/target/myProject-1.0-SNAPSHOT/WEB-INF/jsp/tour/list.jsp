<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tours list | admin</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
</head>
<body>
    <%@include file="../header.jsp"%>
    <h1><b>Туры</b></h1>
    <c:url var="tourDeleteUrl" value="/tour/delete.html"/>
    <form action="${tourDeleteUrl}" method="post">
        <ul>
            <c:forEach var="tour" items="${tours}">
            <c:url var="tourEditUrl" value="/tour/edit.html">
                <c:param name="id" value="${tour.id}"/>
            </c:url>
            <li><input type="checkbox" name="id" value="${tour.id}">Название: <a href="${tourEditUrl}">${tour.name}</a> [ Цена : ${tour.cost}]</li>
            </c:forEach>
        </ul>
        <c:url var="tourEditUrl" value="/tour/edit.html"/>
        <a style="text-decoration: none" href="${tourEditUrl}">Добавить новый тур</a>
        <button class="form-control" style="width: 300px " type="submit">Удалить</button>
    </form>
</body>
</html>