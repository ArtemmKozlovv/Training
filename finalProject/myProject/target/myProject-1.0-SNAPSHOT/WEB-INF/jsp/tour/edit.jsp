<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${not empty tour}">
        <c:set var="title" value="Редактирование тура : ${tour.name}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового тура"/>
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
        <c:url var="tourSaveUrl" value="/tour/save.html"/>
        <form action="${tourSaveUrl}" method="post" style="border: 4px double black; width: 1200px;">
            <c:if test="${not empty tour}">
                <input type="hidden" name="id" value="${tour.id}">
            </c:if>
            <div class="form-floating">
                <h4><b>Название :</b></h4>
                <input type="text" class="form-control" placeholder="Name" name="name" value="${tour.name}">
            </div>
            <div class="form-floating">
                <h4><b>Страна :</b></h4>
                <input type="text" class="form-control"  placeholder="Country" name="country" value="${tour.country}">
            </div>
            <div class="form-floating">
                <h4><b>Цена (коп.) :</b></h4>
                <input type="text" class="form-control" placeholder="Cost" name="cost" value="${tour.cost}">
            </div>
            <div class="form-floating">
                <h4><b>О туре :</b></h4>
                <textarea type="text" class="form-control"  placeholder="AboutTour" name="aboutTour" value="${tour.aboutTour}">${tour.aboutTour}</textarea>
                <%--<input type="text" class="form-control"  placeholder="AboutTour" name="aboutTour" value="${tour.aboutTour}">--%>
            </div>
            <div class="form-floating">
                <h4><b>Дата :</b></h4>
                <input type="text" class="form-control"  placeholder="DateSql" name="dateSql" value="${tour.dateSql}">
            </div>
            <br>
            <button type="submit">Сохранить</button>
        </form>
    </body>
</html>