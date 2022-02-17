<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello people</title>
</head>
<body>
<table>
    <tr>

        <td>
        <c:if test="${empty sessionScope.authenticated}">
        <td>
            <h1>Cargo Delivery</h1>
        </td>
            <c:choose>

                <c:when test="${language.equals('0')}">
                    <td>
                        <form action="${pageContext.request.contextPath}/signIn" method="GET">
                            <input type="submit" value="SignIn">
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/signUp" method="GET">
                            <input type="submit" value="SignUp">
                        </form>
                    </td>
                    <td>
                    <th>
                        <a href="${pathEdit}?adId=${ad.id}">UKR</a>
                    <th>
                    <th>
                        EN
                    </th>

                    </td>
                </c:when>
                <c:when test="${language.equals('1')}">
                    <td>
                        <form action="${pageContext.request.contextPath}/signIn" method="GET">
                            <input type="submit" value="Реєстрація">
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/signUp" method="GET">
                            <input type="submit" value="Новий користувач">
                        </form>
                    </td>
                    <td>
                    <th>
                        <a href="${pathEdit}?adId=${ad.id}">Анг</a>
                    <th>
                    <th>
                        УКР
                    </th>

                    </td>
                </c:when>


            </c:choose>
        </c:if>
        </td>
        <td>

        <c:if test="${not empty sessionScope.authenticated}">
        <td>
            <h1>Cargo Delivery</h1>
        </td>
            <c:choose>
                <c:when test="${language.equals('0')}">
    <td>
                    <form action="view" method='GET'>
                        <input type='submit' id='bold6' class='buttonEnabled' name='logoff' value='Log off'/>
                    </form>
</td>
    <td>
       UKR
    </td>
                </c:when>
                <c:when test="${language.equals('1')}">
                    <td>
                        <form action="view" method='GET'>
                            <input type='submit' id='bold6' class='buttonEnabled' name='logoff'
                                   value='Вийти з особистого кабінету'/>
                        </form>
                    </td>
                    <td>UKR</td>
                </c:when>
            </c:choose>

        </c:if>
    </td>
    </tr>
</table>


<br/>

TEST VISION LANGUAGE <br/>
<c:if test="${language == '1'}">YES 1 </c:if>
<c:if test="${language =='0'}">YES 0 </c:if>
<br/>



</body>
</html>