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
    <c:url var="removeSelectedUrl" value="/user/removeSelect.html"/>
    <form action="${removeSelectedUrl}" method="post">
        <c:if test="${not empty tour}">
            <h1 class="text-center"><b>Выбранный вами тур</b></h1>
            <c:if test="${not empty tour}">
                <input type="hidden" name="id" value="${tour.id}">
            </c:if>
            <div class="col d-flex align-items-start" style="border: 4px double black; padding: 10px;">
                <div>
                    <c:if test="${tour.daysLeft < 8}">
                        <p style="color: darkred"><b>ГОРЯЧИЙ ТУР!</b></p>
                    </c:if>
                    <h2>${tour.name}</h2>
                    <p><b>Страна:</b> ${tour.country}</p>
                    <p><b>О туре:</b> ${tour.aboutTour}</p>
                    <p><b>Дата:</b> ${tour.dateSql}</p>
                    <c:if test="${tour.daysLeft > 7}">
                        <p><b>Цена(BYN):</b> ${tour.costEdit}</p>
                    </c:if>
                    <c:if test="${tour.daysLeft < 8}">
                        <p><b>Горячая цена(BYN):</b> ${tour.costWithDiscountsEdit}</p>
                    </c:if>
                    <button class="btn btn-secondary my-2" type="submit">Отказаться от выбора тура</button>
                </div>
            </div>
    </form>
            <c:url var="requestAddUrl" value="/request/add.html"/>
            <form action="${requestAddUrl}" method="post" style="margin: 0 auto; width: 300px; height: 410px; display: block; bottom: 100px;">
                <h1 class="text-center"><b>Обязательно введите номер вашего телефона, для связи с Вами нашим агентом</b></h1>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Number" name="number">
                    <label for="floatingInput">Номер вашего телефона</label>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">Сохранить</button>
            </form>
        </c:if>
        <c:if test="${empty tour}">
            <h1 class="text-center"><b>Вы не выбрали тур</b></h1>
        </c:if>
</body>
</html>