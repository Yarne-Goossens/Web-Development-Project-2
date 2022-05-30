<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheek</title>
    <link rel="stylesheet" href="style/Normalize.css">
    <link rel="stylesheet" href="style/Stylesheet.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="icon" href="images/biblogo.png" type="image/x-icon">
</head>

<body>
<jsp:include page="header.jsp">
    <jsp:param name="current" value="zoekForm"/>
</jsp:include>

<main>
    <c:if test="${not empty errors}">
        <div id="error" class="alert alert-danger">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <article id="form" class="container">
        <h2>Zoek voor een boek</h2>
        <form action="Controller" method="post" novalidate>
            <p><label for="boek">Titel: </label><input type="text" id="boek" name="boek" value="${boek}" autofocus></p>
            <input type="hidden" name="command" value="resultaat">
            <p><input type="submit" id="verstuur" value="indienen"></p>
        </form>
    </article>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>