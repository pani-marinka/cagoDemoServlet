<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${locale }"/>
    <fmt:setBundle basename="messages"/>
    <title>Hello people</title>

    <br/>
    <table>
        <tr>
            <td>
                <h1>Cargo Delivery</h1>
            </td>
        </tr>
        <tr>
            <td>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <c:if test="${empty sessionScope.authenticated}">
                <td>
                    <form action="${pageContext.request.contextPath}/signIn" method="GET">
                        <input type="submit" value="<fmt:message key="login"/>">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/signUp" method="GET">
                        <input type="submit" value="<fmt:message key="logup"/>">
                    </form>

                </td>
            </c:if>
            <c:if test="${not empty sessionScope.authenticated}">

                <td>
                    <h1><fmt:message key="greeting"/> ${login }! </h1>
                </td>
                <td>&nbsp;&nbsp;
                    <form action="view" method='GET'>
                        <input type='submit' id='bold9' class='buttonEnabled' name='logoff'
                               value="<fmt:message key="logoff"/>"/>
                    </form>
                </td>
            </c:if>


            <td>
                &nbsp;&nbsp;
                <a href="setLocale?locale=ukr">UKR</a> |
                <a href="setLocale?locale=en">EN</a>
            </td>


        </tr>
    </table>
    <%--    </fmt:bundle>--%>
    <br/>
    <br/>

</head>
<body>
<h1><fmt:message key="greeting"/>! </h1>


</body>
</html>