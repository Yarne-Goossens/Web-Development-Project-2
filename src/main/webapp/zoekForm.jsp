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
            <li><a href="Controller?command=zoekForm" class="here">Zoek</a></li>
            <li><a href="Controller?command=voegtoe">Voeg toe</a></li>
            <li><a href="Controller?command=overzicht">Overzicht</a></li>
        </ul>
    </nav>
</header>

<main>
    <article id="form" class="container">
        <h2>Zoek voor een boek</h2>
        <form action="Controller" method="post" novalidate>
            <p><label for="titel">Titel</label><input type="text" id="titel" name="titel" required autofocus></p>
            <input type="hidden" name="command" value="confirmation">
            <p><input type="submit" id="verstuur" value="indienen"></p>
        </form>
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