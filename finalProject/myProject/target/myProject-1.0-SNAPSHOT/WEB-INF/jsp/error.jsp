<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.kozlov.epam.myproject.entity.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>ERROR PAGE</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
</head>
<body>
	<%@include file="header.jsp"%>
	<div>
		<div>
			<h1>Что-то пошло не так...</h1>
			<br /><br /><br />
			<a>${param.user_message}</a>
			<a>${user_message}</a>
			<br/><br/><br/>
		</div>
	</div>

</body>
</html>