<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${sessionScope.title}</title>
</head>
<body>
${sessionScope.quest}

<p>${sessionScope.story}</p>

<p>${sessionScope.prompt}</p>

<form action="getAnswer" method="post">
    <c:forEach items="${sessionScope.listOfAnswers}" var="answer">
        <input type="submit" name="selectedAnswer" value="${answer}">
    </c:forEach>
</form>

<p>${sessionScope.answerStory}</p>

<% if (session.getAttribute("answerStory") != null && !session.getAttribute("answerStory").equals("")) { %>
<form action="nextStep" method="post">
    <button type="submit">Next step</button>
</form>
<% } %>

</body>
</html>
