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
                <td>
                    <form action="${pageContext.request.contextPath}/calculation" method='GET'>
                        <input type='submit' id='bold22' class='buttonEnabled'   value="<fmt:message key="calculation"/>"/>
                    </form>
                                 </td>
            </c:if>
            <c:if test="${not empty sessionScope.authenticated}">
                <td></td>
                <td>
                    <h1><fmt:message key="greeting"/> ${login }! </h1>&nbsp;
                    &nbsp;
                </td>
                <td>
                <td>
                    <form action="${pageContext.request.contextPath}/calculation" method='GET'>
                        <input type='submit' id='bold23' class='buttonEnabled'   value="<fmt:message key="calculation"/>"/>
                    </form>
                </td>
                </td>
                <td>
                    <form action="view" method='GET'>
                        <input type='submit' id='bold8' class='buttonEnabled' name='logoff'
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


</head>
<body>
<br/>
<br/>
<br/>
<h1><fmt:message key="mainTableTariff"/></h1>
    <br/>
<table id="maintable2" width="80%" border="1" align="center">
    <tr>
        <th><fmt:message key="direction"/></th>
        <th><fmt:message key="distance"/></th>
        <th><fmt:message key="capacity"/></th>
        <th><fmt:message key="cost"/></th>
    </tr>
    <c:forEach items="${routsCost}" var="routs">
        <tr>
            <c:forEach items="${routs.distanceCollection}" var="distanceOne">
                <td>
                    from: <a
                        href="setType?typeFrom=${distanceOne.cityFromNameEn}"> ${distanceOne.cityFromNameEn} </a><br/>
                    to: <a href="setType?typeTo=${distanceOne.cityToNameEn}"> ${distanceOne.cityToNameEn}</a><br/>

                </td>
                <td>
                        ${distanceOne.distance}km
                </td>

            </c:forEach>

            <td>

                <table>

                    <c:forEach items="${routs.tariffCosts}" var="tarrifCollection">
                        <tr>
                            <td>
                                    ${tarrifCollection.tonnageVolume}&nbsp;(tonn)<br/>
                            </td>

                        </tr>
                    </c:forEach>
                </table>

            </td>
            <td>
                <table>

                    <c:forEach items="${routs.tariffCosts}" var="tarrifCollection">
                        <tr>

                            <td>
                                    ${tarrifCollection.cost}&nbsp;ghr/km<br/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </td>

        </tr>
    </c:forEach>

</table>

<form action="setType" method='GET'>
    <input type='submit' id='bold9' class='buttonEnabled' name='resetCity'
           value="<fmt:message key="resetCity"/>"/>

    <br/>
    <h1><fmt:message key="mainTableTariffAny"/></h1>
    <table id="maintable" width="80%" border="1" align="center">

        <tr>

            <th><fmt:message key="capacity"/></th>
            <th><fmt:message key="cost"/></th>
        </tr>
        <tr>
            <td>
                <table>
                    <c:forEach items="${tariffAny}" var="tariffAnyWhere">
                        <tr>
                                ${tariffAnyWhere.tonnageVolume} (tonn) <br/>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td>
                <table border="1">
                    <c:forEach items="${tariffAny}" var="tariffAnyWhere">
                        <tr>
                                ${tariffAnyWhere.cost}&nbsp;ghr/km <br/>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    <br/>
    <br/>
<%--    <form action="${pageContext.request.contextPath}/calculation" method='GET'>--%>
<%--        <input type='submit' id='bold10' class='buttonEnabled' name='calculationDelivery'--%>
<%--               value="<fmt:message key="calculation"/>"/>--%>

    <br/>
</body>
</html>