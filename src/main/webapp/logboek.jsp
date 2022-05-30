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
    <jsp:param name="current" value="logboek"/>
</jsp:include>

<main>
    <c:choose>
        <c:when test="${not empty paginas}">
            <h2>Overzicht</h2>
            <form action="Controller" method="post" novalidate>
                <input type="hidden" name="command" value="reset">
                <p><input type="submit" id="verstuur" value="Logboek resetten"></p>
            </form>
            <table>
                <thead>
                <tr>
                    <th>Pagina</th>
                    <th>Tijd</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="pagina" items="${paginas}">
                    <tr>
                        <td>${pagina.pagina}
                        </td>
                        <td>${pagina.tijd}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Er zijn geen paginas opgeslagen in het logboek</p>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>