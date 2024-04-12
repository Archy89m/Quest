<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${sessionScope.title}</title>
</head>
<body>

<h3>Quest story:</h3>
<p>${sessionScope.story}</p>

<h4>Prompt:</h4>
<p>${sessionScope.prompt}</p>

<p>${sessionScope.optionTitle}</p>

<form action="getAnswer" method="post">
    <c:forEach items="${sessionScope.listOfAnswers}" var="answer">
        <input type="submit" name="selectedAnswer" value="${answer}">
    </c:forEach>
</form>

<p>${sessionScope.answerStory}</p>

<% if (session.getAttribute("answerStory") != null && !session.getAttribute("answerStory").equals("")) { %>
<form action="start" method="post">
    <button type="submit">Next step</button>
</form>
<% } %>

</body>
</html>
