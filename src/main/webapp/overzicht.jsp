<%@ page import="domain.model.Boek" %>
<%@ page import="java.util.ArrayList" %>
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
            <li><a href="Controller?command=zoekForm">Zoek</a></li>
            <li><a href="Controller?command=voegtoe">Voeg toe</a></li>
            <li><a href="Controller?command=overzicht" class="here">Overzicht</a></li>
        </ul>
    </nav>
</header>

<main>
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

        <%
            ArrayList<Boek> boeken = (ArrayList<Boek>) request.getAttribute("boeken");
            for (Boek b : boeken) {
        %>

        <tr>
            <td><%= b.getTitel()%>
            </td>
            <td><%= b.getAuteur()%>
            </td>
            <td><%= b.getAantal()%>
            </td>
            <td><%= b.getGenre()%>
            </td>
            <td><%= b.getIsbn()%>
            </td>
            <td><%= b.getLeeftijd()%>
            </td>
            <td><a href="#">Wijzig</a></td>
            <td><a href="#">Verwijder</a></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
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