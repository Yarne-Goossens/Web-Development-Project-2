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
    <c:choose>
        <c:when test="${not empty boek}">
            <article><h2>Zoekresultaat</h2>
                <p>We vonden dit boek voor "${param.boek}":</p>
                <ul>
                    <li>Naam: ${boek.titel}
                    </li>
                    <li>Auteur: ${boek.auteur}
                    </li>
                    <li>Aantal: ${boek.aantal}
                    </li>
                    <li>Genre: ${boek.genre}
                    </li>
                    <li>ISBN: ${boek.isbn}
                    </li>
                    <li>Leeftijd: ${boek.leeftijd}
                    </li>
                </ul>
            </article>
        </c:when>
        <c:otherwise>
            <p>We konden het boek genaamd ${param.boek} helaas niet vinden. </p>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>