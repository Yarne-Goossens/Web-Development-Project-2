<header>
  <section class="title">
    <a href="Controller?command=index"><img src="images/biblogo.png" alt="logo"></a>
    <h1>Bibliotheek</h1>
  </section>

  <nav>
    <ul>
      <li><a href="Controller?command=index" ${param.current.equals("index")?"class='here'":""}>Home</a></li>
      <li><a href="Controller?command=zoekForm" ${param.current.equals("zoekForm")?"class='here'":""}>Zoek</a></li>
      <li><a href="Controller?command=voegtoe" ${param.current.equals("voegtoe")?"class='here'":""}>Voeg toe</a></li>
      <li><a href="Controller?command=overzicht" ${param.current.equals("overzicht")?"class='here'":""}>Overzicht</a></li>
    </ul>
  </nav>
</header>