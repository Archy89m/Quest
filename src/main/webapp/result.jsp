<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>THE END</title>
    <link rel="stylesheet" href="css/styles.css?v=2">
</head>
<body>

<h3 class="quest-title">THE END</h3>

<p class="story-text">${sessionScope.answerStory}</p>
</body>

<form action="start" method="get" class="button-container">
    <input type="submit" value="Return to start" class="quest-button">
</form>
</html>
