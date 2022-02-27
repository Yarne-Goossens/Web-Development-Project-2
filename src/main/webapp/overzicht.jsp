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
            <li><a href="index.jsp">Home</a></li>
            <li><a href="voegtoe.jsp">Voeg toe</a></li>
            <li><a href="overzicht.jsp" class="here">Overzicht</a></li>
        </ul>
    </nav>
</header>

<main>
    <h1>Overzicht</h1>
    <table>
        <tr>
            <th>Titel</th>
            <th>Auteur</th>
            <th>Aantal</th>
            <th>Genre</th>
            <th>ISBN-nummer</th>
            <th>Leeftijdsclassificatie</th>
            <th>Wijzig</th>
            <th>Verwijder</th>
        </tr>
        <tr>
            <td>The Lord of the Rings</td>
            <td>J.R.R. Tolkien</td>
            <td>5</td>
            <td>Fantasie</td>
            <td>9780261103252</td>
            <td>15</td>
            <td><a href="#">Wijzig</a></td>
            <td><a href="#">Verwijder</a></td>
        </tr>
        <tr>
            <td>The Maze Runner</td>
            <td>James Dashner</td>
            <td>7</td>
            <td>Avontuur</td>
            <td>9781908435132</td>
            <td>15</td>
            <td><a href="#">Wijzig</a></td>
            <td><a href="#">Verwijder</a></td>
        </tr>
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