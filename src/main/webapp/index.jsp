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
    <jsp:param name="current" value="index"/>
</jsp:include>

<main id="index">
    <article>
        <h2>
            Info
        </h2>

        <p>
            Op deze site kan je allerlei info vinden over de biliotheek.
        </p>

        <p>
            Je kan hier zien welke boeken er beschikbaar zijn in de bibliotheek en hoeveel er nog zijn.
        </p>
        <p> Het boek waarvan we het grootste aantal hebben is
            <strong>${grootste.getTitel()}
            </strong></p>

    </article>

    <article>
        <h2>Foto van de bibliotheek</h2>
        <img src="images/bibliotheek.jpg" alt="foto bibliotheek">
    </article>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>