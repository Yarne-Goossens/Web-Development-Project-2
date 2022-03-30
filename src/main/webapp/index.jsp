<%@ page import="domain.model.Boek" %>
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
<header>
    <section class="title">
        <a href="index.jsp"><img src="images/biblogo.png" alt="logo"></a>
        <h1>Bibliotheek</h1>
    </section>

    <nav>
        <ul>
            <li><a href="Controller?command=index" class="here">Home</a></li>
            <li><a href="Controller?command=zoekForm">Zoek</a></li>
            <li><a href="Controller?command=voegtoe">Voeg toe</a></li>
            <li><a href="Controller?command=overzicht">Overzicht</a></li>
        </ul>
    </nav>
</header>

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
        <p> Het boek waarvan we het grootste aantal hebben is <strong><%= ((Boek)request.getAttribute("grootste")).getTitel() %></strong></p>

    </article>

    <article>
        <img src="images/bibliotheek.jpg" alt="foto bibliotheek">
    </article>
</main>

<footer>
    <section>
        <h2>Copyright</h2>

        <address>
            <ul>
                <li>©Yarne Goossens</li>
            </ul>
        </address>
    </section>
</footer>
</body>

</html>