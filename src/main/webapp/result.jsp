
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Finish</title>
</head>
<body>

<h3>THE END</h3>

<p>${sessionScope.answerStory}</p>
</body>

<form action="start" method="get">
    <input type="submit" value="Return to start">
</form>
</html>
