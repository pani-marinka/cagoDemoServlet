<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Sign In</title>
</head>
<body>
<h1>Customer Sign In</h1>
<c:choose>
<c:when test="${language.equals('0')}">
<c:if test="${violations != null}">
    <c:forEach items="${violations}" var="violation">
        <p>${violation}.</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/signIn" method="post">
    <label for="login">Login : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="text" name="login" id="login" value="${login}">
    <br/>
    <br/>

    <label for="password">Password : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="password" name="pass" id="password" value="${pass}">
    <br/>
    <br/>
        <input type="submit" name="signin" value="Sign In">
</form>
<br/>
<br/>
<form action=${pathMain}  method='GET'>
    <input type='submit' value='To Main Page'>
</form>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/signUp" method="GET">
    <input type="submit" value="SignUp">
</form>
</c:when>
</c:choose>
</body>
</html>