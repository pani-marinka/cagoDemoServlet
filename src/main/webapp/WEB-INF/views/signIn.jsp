<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<head>
    <fmt:setLocale value="${locale }"/>
    <fmt:setBundle basename="messages" />
    <meta charset="UTF-8">
    <title><fmt:message key="сustomerSignIn"/></title>
</head>
<body>
<h1><fmt:message key="сustomerSignIn"/>&nbsp; &nbsp;
<a href="setLocale?locale=ukr">UKR</a> |
<a href="setLocale?locale=en">EN</a>
    <br/>
</h1>


<c:if test="${violations != null}">
    <c:forEach items="${violations}" var="violation">
        <p>${violation}.</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/signIn" method="post">
    <label for="login"><fmt:message key="user"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="text" name="login" id="login" value="${login}">
    <br/>
    <br/>

    <label for="password"><fmt:message key="pass"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="password" name="pass" id="password" value="${pass}">
    <br/>
    <br/>
        <input type="submit" name="signin" value="<fmt:message key="login"/>">
</form>
<br/>
<br/>
<form action=${pathMain}  method='GET'>
    <input type='submit' value='<fmt:message key="main"/>'>
</form>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/signUp" method="GET">
    <input type="submit" value="<fmt:message key="logup"/>">
</form>

</body>
</html>