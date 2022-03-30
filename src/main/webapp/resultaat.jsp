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
            <li><a href="Controller?command=index">Home</a></li>
            <li><a href="Controller?command=zoek" class="here">Zoek</a></li>
            <li><a href="Controller?command=voegtoe">Voeg toe</a></li>
            <li><a href="Controller?command=overzicht">Overzicht</a></li>
        </ul>
    </nav>
</header>

<main id="index">
    <h2>Zoekresultaat</h2>
    <% if (request.getAttribute("boek") != null) {%>
    <p>We vonden dit boek <%= request.getParameter("boek")%>:</p>
    <% Boek b = (Boek) request.getAttribute("boek");%>
    <ul>
        <li>Naam: <%= b.getTitel()%>
        </li>
        <li>Auteur: <%= b.getAuteur()%>
        </li>
        <li>Aantal: <%= b.getAantal()%>
        </li>
        <li>Genre: <%= b.getGenre()%>
        </li>
        <li>ISBN: <%= b.getIsbn()%>
        </li>
        <li>Leeftijd: <%= b.getLeeftijd()%>
        </li>
    </ul>

    <%} else {%>
    <p>We konden het boek genaamd <%= request.getParameter("boek")%> helaas niet vinden. </p>
    <%}%>
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