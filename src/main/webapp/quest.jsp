<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${sessionScope.title}</title>
    <link rel="stylesheet" href="css/styles.css?v=2">
</head>
<body>

<h3 class="quest-title">Quest story:</h3>
<p class="story-text">${sessionScope.story}</p>

<p class="story-text">${sessionScope.prompt}</p>

<p class="option-title">${sessionScope.optionTitle}</p>

<div class="button-container">
    <form action="getAnswer" method="post">
        <c:forEach items="${sessionScope.listOfAnswers}" var="answer">
            <input type="submit" name="selectedAnswer" value="${answer}" class="quest-button">
        </c:forEach>
    </form>
</div>

<c:if test="${not empty sessionScope.answerStory}">
    <p class="story-text">${sessionScope.answerStory}</p>
</c:if>

<% if (session.getAttribute("answerStory") != null && !session.getAttribute("answerStory").equals("")) { %>
<form action="start" method="post" class="button-container">
    <input type="submit" value="Next step" class="quest-button">
</form>
<% } %>

</body>
</html>
