<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quests</title>
</head>
<body>

<c:forEach var="quest" items="${sessionScope.quests}">
    <form action="catalog" method="post">
        <input type="hidden" name="questValue" value="${quest}" />
        <input type="submit" value="${quest}" />
    </form>
</c:forEach>

<form action="start" method="get">
    <input type="submit" value="Return to the start page">
</form>

</body>
</html>