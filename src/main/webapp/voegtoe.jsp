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
    <jsp:param name="current" value="voegtoe"/>
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
        <h2>Voeg een boek toe</h2>
        <form action="Controller?command=add" method="POST" novalidate>
            <p class="form-group">
                <label class="control-label" for="titel">Titel:</label>
                <input id="titel" name="titel" type="text"
                       value="${titelPreviousValue}" class="${titelHasErrors? 'error' : ''}" required>
            </p>
            <p class="form-group">
                <label class="control-label" for="auteur">Auteur:</label>
                <input id="auteur" name="auteur" type="text"
                       value="${auteurPreviousValue}" class="${auteurHasErrors? 'error' : ''}" required>
            </p>
            <p class="form-group">
                <label for="aantal">Aantal:</label>
                <input
                        id="aantal" name="aantal" type="number"
                        min="0" value="${aantalPreviousValue}" class="${aantalHasErrors? 'error' : ''}">
            </p>
            <p class="form-group">
                <label class="control-label" for="genre">Genre:</label>
                <input id="genre" name="genre" type="text"
                       value="${genrePreviousValue}" class="${genreHasErrors? 'error' : ''}" required>
            </p>
            <p class="form-group">
                <label class="control-label" for="isbn">ISBN-nummer:</label>
                <input id="isbn" name="isbn" type="text"
                       value="${isbnPreviousValue}" class="${isbnHasErrors? 'error' : ''}" required>
            </p>
            <p class="form-group">
                <label class="control-label" for="leeftijd">Leeftijd:</label>
                <input id="leeftijd" name="leeftijd" type="number"
                       min="0" value="${leeftijdPreviousValue}" class="${leeftijdHasErrors? 'error' : ''}" required>
            </p>
            <p><input type="submit" id="verstuur" value="Indienen"></p>
        </form>
    </article>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>