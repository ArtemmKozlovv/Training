<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Список туров</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    </head>

    <body class="text-center">
    <%@include file="header.jsp"%>
        <div class="container px-4 py-5" id="hanging-icons">
            <h2 class="pb-2 border-bottom">Список туров</h2>
            <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
                <c:forEach var="tour" items="${tours}">
                    <c:url var="SelectTourForUserUrl" value="/user/select.html">
                        <%--<input type="hidden" name="id" value="${tour.id}">
                        <input type="hidden" name="name" value="${tour.name}">--%>
                        <c:param name= "id" value="${tour.id}"/>
                    </c:url>
                    <form action="${SelectTourForUserUrl}" method="post">
                    <div class="col d-flex align-items-start" style="border: 4px double black; padding: 10px;">
                        <div>
                            <c:if test="${tour.daysLeft < 8}">
                                <p style="color: darkred"><b>ГОРЯЧИЙ ТУР!</b></p>
                            </c:if>
                            <p><b>Название:</b> ${tour.name}</p>
                            <p><b>Страна:</b> ${tour.country}</p>
                            <p><b>О туре:</b> ${tour.aboutTour}</p>
                            <p><b>Дата:</b> ${tour.dateSql}</p>
                            <c:if test="${tour.daysLeft > 7}">
                                <p><b>Цена(BYN):</b> ${tour.costEdit}</p>
                            </c:if>
                            <c:if test="${tour.daysLeft < 8}">
                                <p><b>Горячая цена(BYN):</b> ${tour.costWithDiscountsEdit}</p>
                            </c:if>
                            <button class="btn btn-secondary my-2" type="submit">Выбрать тур</button>
                        </div>
                    </div>
                    </form>
                </c:forEach>
            </div>
        </div>

    </body>
</html>