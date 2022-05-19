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
    <jsp:param name="current" value="overzicht"/>
</jsp:include>

<main>
    <c:choose>
        <c:when test="${not empty boeken}">
        <h2>Overzicht</h2>
        <table>
            <thead>
            <tr>
                <th>Titel</th>
                <th>Auteur</th>
                <th>Aantal</th>
                <th>Genre</th>
                <th>ISBN-nummer</th>
                <th>Leeftijd</th>
                <th>Wijzig</th>
                <th>Verwijder</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="boek" items="${boeken}">
                <tr>
                    <td>${boek.titel}
                    </td>
                    <td>${boek.auteur}
                    </td>
                    <td>${boek.aantal}
                    </td>
                    <td>${boek.genre}
                    </td>
                    <td>${boek.isbn}
                    </td>
                    <td>${boek.leeftijd}
                    </td>
                    <td><a href="#">Wijzig</a></td>
                    <td><a href="#">Verwijder</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:when>
        <c:otherwise>
            <p>Er zijn geen boeken </p>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>