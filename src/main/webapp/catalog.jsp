<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quest catalog</title>
    <link rel="stylesheet" href="css/styles.css?v=2">
</head>
<body>

<h3 class="quest-title">Quest catalog</h3>

<c:forEach var="quest" items="${sessionScope.quests}">
    <form action="catalog" method="post" class="button-container">
        <input type="hidden" name="questValue" value="${quest}" />
        <input type="submit" value="${quest}" class="quest-button"/>
    </form>
</c:forEach>

</body>
</html>