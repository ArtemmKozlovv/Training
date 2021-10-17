<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <c:url var="urlTourList" value="/tourList.html"/>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <c:if test="${not empty currentUser}">
                    <li><a href="${urlTourList}" class="nav-link px-2 text-white">Туры</a></li>
                </c:if>
                <c:url var="urlAbout" value="/about.html"/>
                <li><a href="${urlAbout}" class="nav-link px-2 text-white">О нас</a></li>
            </ul>

            <div class="text-end">
                <c:url var="urlMain" value="/index.html"/>
                <c:url var="urlRegister" value="/register.html"/>
                <c:url var="urlLogout" value="/logout.html"/>
                <c:url var="urlProfile" value="/user/profile.html"/>
                <c:if test="${empty currentUser}">
                    <a href="${urlMain}"><button type="button" class="btn btn-outline-light me-2">Войти</button></a>
                    <a href="${urlRegister}"><button type="button" class="btn btn-warning">Зарегистрироваться</button></a>
                </c:if>
                <c:if test="${not empty currentUser}">
                    <a href="${urlProfile}"><button type="button" class="btn btn-outline-light me-2">Профиль</button></a>
                    <a href="${urlLogout}"><button type="button" class="btn btn-warning">Выйти</button></a>
                </c:if>
            </div>
        </div>
    </div>
</header>