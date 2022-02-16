<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello people</title>
</head>
<body>
<h1>Cargo Delivery</h1>
<br/>

<br/>
<c:if test="${language == '1'}">YES 1 </c:if>
<c:if test="${language =='0'}">YES 0 </c:if>
<br/>
<c:if test="${empty sessionScope.authenticated}">
    <c:choose>
        <c:when test="${language.equals('1')}">
            <TABLE>
                <tr>
                   <th>
            <form action="${pageContext.request.contextPath}/signIn" method="GET">
               <input type="submit" value="SignIn">
            </form>
                    </th>
            <th>
            <form action="${pageContext.request.contextPath}/signUp" method="GET">
                <input type="submit" value="SignUp">
            </form>
            </th>

            <th>
                <a href="${pathEdit}?adId=${ad.id}">UKR</a>
                    <th>
            <th>
               EN
                </th>

            </tr>
            </TABLE>
        </c:when>


        <c:when test="${language=='0'}">

                       <form  method='GET'>
                <input type='submit' class='buttonEnabled' name='deck' value='Create Language'/>
            </form>

        </c:when>
    </c:choose>

</c:if>


<c:if test="${not empty sessionScope.authenticated}">
    <c:choose>
        <c:when test="${language=='1'}">



            <th>
                <form action="view" method='GET' >
                    <input type='submit' id='bold6'   class='buttonEnabled' name='logoff' value='Log off'/>
                </form>
            </th>

                <a href="${pathEdit}?adId=${ad.id}">EN</a>
                UKR
        </c:when>


        <c:when test="${language=='0'}">
            CREATE YOUR DECK
            <form  method='GET'>
                <input type='submit' class='buttonEnabled' name='deck' value='Create Language0'/>
            </form>

            <td>
                <a href="${pathEdit}?adId=${ad.id}">EN</a>
            </td>
            <td>
                UKR
            </td>
        </c:when>
    </c:choose>
</c:if>


</body>
</html>