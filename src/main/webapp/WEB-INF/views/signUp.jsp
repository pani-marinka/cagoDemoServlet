<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Sign Up</title>
</head>
<body>
<h1>Customer Sign Up</h1>

<c:if test="${violations != null}">
    <c:forEach items="${violations}" var="violation">
        <p>${violation}.</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/signUp" method="post">
    <label for="firstname">First Name : &nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="text" name="firstname" id="firstname" value="${firstname}">
    <br/>
    <br/>
    <label for="lastname">Last Name: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="text" name="lastname" id="lastname" value="${lastname}">
    <br/>
    <br/>
    <label for="email">Email:&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="text" name="email" id="email" value="${email}">
    <br/>
    <br/>
    <label for="login">Login      : &nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="text" name="login" id="login" value="${login}">
    <br/>
    <br/>

    <label for="password">Password : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
    <input type="password" name="pass" id="password" value="${pass1}">
    <br/>
    <br/>
    <label for="password">Password again:&nbsp </label>
    <input type="password" name="pass2" id="password2" value="${pass2}">
    <br/>
    <br/>
    <input type="submit" name="signup" value="Sign Up">
</form>
<br/>
<br/>
<form action=${pathMain}  method='GET'>
    <input type='submit' value='To Main Page'>
</form>




</body>
</html>