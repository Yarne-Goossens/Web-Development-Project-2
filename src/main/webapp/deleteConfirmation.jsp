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
    <h2>Bevestiging</h2>
    <p>Weet je zeker dat je het boek ${boek.titel} wilt verwijderen?</p>
    <p><a href="Controller?command=delete&id=${boek.id}">Verwijder het boek.</a></p>
    <p><a href="Controller?command=overzicht">Cancel</a> Ga terug naar de overzichtspagina.</p>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>