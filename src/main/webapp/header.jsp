<header>
    <section class="title">
        <a href="Controller?command=default" id="homelogo"><img src="images/biblogo.png" alt="logo"></a>
        <h1>Bibliotheek</h1>
    </section>

    <nav>
        <ul>
            <li><a href="Controller?command=default" ${param.current.equals("index")?"class='here'":""} id="home">Home</a></li>
            <li><a href="Controller?command=zoekForm" ${param.current.equals("zoekForm")?"class='here'":""} id="zoek">Zoek</a></li>
            <li><a href="Controller?command=voegtoe" ${param.current.equals("voegtoe")?"class='here'":""} id="voegtoe">Voeg toe</a></li>
            <li><a href="Controller?command=overzicht" ${param.current.equals("overzicht")?"class='here'":""} id="overzicht">Overzicht</a></li>
            <li><a href="Controller?command=logboek" ${param.current.equals("logboek")?"class='here'":""} id="logboek">Logboek</a>
            </li>
        </ul>
    </nav>
</header>