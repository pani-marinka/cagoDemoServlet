<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <fmt:setLocale value="${locale }"/>
    <fmt:setBundle basename="messages"/>
    <meta charset="UTF-8">
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


                </td>

            </c:if>
            <c:if test="${not empty sessionScope.authenticated}">
                <td></td>
                <td>
                    <h1><fmt:message key="greeting"/> ${login }! </h1>&nbsp;
                    &nbsp;
                </td>

            </c:if>


            <td>
                &nbsp;&nbsp;
                <a href="setLocale?locale=ukr">UKR</a> |
                <a href="setLocale?locale=en">EN</a>
            </td>


        </tr>
    </table>
    <title><h1><fmt:message key="calcuclatePerson"/> </h1>&nbsp;</title>
</head>
<body>
<h1><fmt:message key="infoDelivery"/></h1>
<h2><fmt:message key="txtInfoDelivery"/></h2>
<br/>
<br/>

<table  width="80%"  align="center">

    <tr>
        <td>

            <center align="left"><h2><fmt:message key="fromDelivery"/></h2></center>
        <br/>

            <form method='GET'>
                <h2><fmt:message key="senderCity"/></h2>
                <select name="cityFromSel">
                    <c:forEach items="${cityFrom}" var="cityFrom">
                        <option value=${cityFrom.idCity}> ${cityFrom.cityNameEn} </option>
                    </c:forEach>
                </select>
                <%--            <input type='submit' class='buttonEnabled'  value='<fmt:message key="fromDeliverySend"/>' />--%>
                <%--        </form>--%>
                <%--        </td>--%>
                <%--        <td>--%>
                <%--            <c:choose>--%>
                <%--            <c:when test="${deck > '0'}">--%>
                <%--            <form method='GET'>--%>

                <br/>


                <h2><fmt:message key="deliveryCity"/></h2>
                <select name="cityToSel">

                    <c:forEach items="${cityTo}" var="cityTo">
                        <option value=${cityTo.idCity}> ${cityTo.cityNameEn} </option>
                    </c:forEach>
                </select>
                <br/>
                <br/>
                <br/>
                <input type='submit' class='buttonEnabled'  value='<fmt:message key="ToDeliverySend"/>' />
            </form>
        </td>
        <td>


            <table >



                <tr>
                    <center align="left"><h2><fmt:message key="calculateWeight"/></h2></center>
                    <br/>
                </tr>
                <tr>
                    <td>

                        <label ><fmt:message key="length"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                    </td>
                    <td>
                        <input type="text" name="length" id="lenght" value="${length}">
                        <br/>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label ><fmt:message key="wide"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                    </td>
                    <td>
                        <input type="text" name="wide" id="wide" value="${wide}">
                        <br/>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label ><fmt:message key="hight"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                    </td>
                    <td>
                        <input type="text" name="hight" id="hight" value="${hight}">
                        <br/>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label ><fmt:message key="place"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                    </td><td>
                    <input type="text" name="place" id="place" placeholder="1" value="${place}">
                    <br/>
                    <br/>
                </td>
                </tr>
                <tr>
                    <td>
                        <label ><fmt:message key="actualWeight"/> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
                    </td><td>
                    <input type="text" name="place" id="actualWeight" placeholder="1" value="${place}">
                    <br/>
                    <br/>
                </td>
                </tr>
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/calculation" method='GET'>
                            <input type='submit' id='bold8' class='buttonEnabled' name='count'
                                   value="<fmt:message key="count"/>"/>
                        </form>
                        <%--            <input type="submit" name="count" value='<fmt:message key="count"/>'>--%>
                    </td>
                    <td></td>
                </tr>
            </table>


        </td>
    </tr>
    <tr>
        <td>


<%--            </c:when>--%>
<%--            </c:choose>--%>
        </td>
        <td>
        </td>
    </tr>
</table>
<br/>
<br/>
<table>

    <tr>
        <td>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <c:if test="${empty sessionScope.authenticated}">

        <td>
            <h2><fmt:message key="infoOrder"/></h2>

        </td>

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
            <td></td>

            <td>
            <td>

                <form action="${pageContext.request.contextPath}/calculation" method='Post'>
                    <input type='submit' id='bold24' class='buttonEnabled'   value="<fmt:message key="orderdelivery"/>"/>
                </form>
            </td>
            </td>
            <td>
                <form action="view" method='GET'>
                    <input type='submit' id='bold18' class='buttonEnabled' name='logoff'
                           value="<fmt:message key="logoff"/>"/>
                </form>
            </td>
        </c:if>
        <td>
            <form action=${pathMain}  method='GET'>
                <input type='submit' value='<fmt:message key="main"/>'>
            </form>
        </td>

        <td>
            &nbsp;&nbsp;
            <a href="setLocale?locale=ukr">UKR</a> |
            <a href="setLocale?locale=en">EN</a>
        </td>


    </tr>
</table>
</body>
</html>