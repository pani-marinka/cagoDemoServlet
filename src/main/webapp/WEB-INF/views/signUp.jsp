<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<head>
    <fmt:setLocale value="${locale }"/>
    <fmt:setBundle basename="messages" />
    <meta charset="UTF-8">
    <title><fmt:message key="сustomerSignUp"/></title>
    <script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
<h1><fmt:message key="сustomerSignUp"/></h1> &nbsp;&nbsp;&nbsp;
<a href="setLocale?locale=ukr">UKR</a> |
<a href="setLocale?locale=en">EN</a>

<c:if test="${violations != null}">
    <c:forEach items="${violations}" var="violation">
        <p>${violation}.</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/signUp" method="post">
    <table>
        <tr>
            <td>
    <label for="firstname"><fmt:message key="firstName"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
            </td>
            <td>
    <input type="text" name="firstname" id="firstname" value="${firstname}">
    <br/>
    <br/>
            </td>
        </tr>
        <tr>
            <td>
    <label for="lastname"><fmt:message key="lastName"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
            </td>
            <td>
    <input type="text" name="lastname" id="lastname" value="${lastname}">
    <br/>
    <br/>
            </td>
        </tr>
        <tr>
            <td>
    <label for="email"><fmt:message key="email"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
            </td>
            <td>
    <input type="text" name="email" id="email" value="${email}">
    <br/>
    <br/>
            </td>
        </tr>
        <tr>
            <td>
    <label for="login"><fmt:message key="user"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
            </td><td>
    <input type="text" name="login" id="login" value="${login}">
    <br/>
    <br/>
        </td>
        </tr>
        <tr>
            <td>

    <label for="password"><fmt:message key="pass"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                </td><td>
    <input type="password" name="pass" id="password" value="${pass1}">

    <br/>
    <br/>
        </td>
        </tr>
        <tr>
            <td>
    <label for="password"><fmt:message key="pass2"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
            </td><td>
    <input type="password" name="pass2" id="password2" value="${pass2}">
    <br/>
    <br/>
        </td>
        </tr>
        <tr>
            <td>
    <fmt:message key="language"/>
    <select name="language">
        <option value="0"> EN</option>
        <option value="1"> UKR</option>
    </select>
    <br/>
    <br/>
            </td>
            <td></td>
        </tr>
        <tr>
            <td>
    <div class="g-recaptcha"
         data-sitekey="6LcZ9IMeAAAAADDLJw9oSMURo3CpB5bqFS78HFP6"></div>
    <input type="submit" name="signup" value='<fmt:message key="logup"/>'>
            </td>
            <td></td>
        </tr>
    </table>
</form>
<br/>
<br/>
<form action=${pathMain}  method='GET'>
    <input type='submit' value='<fmt:message key="main"/>'>
</form>

</body>
</html>