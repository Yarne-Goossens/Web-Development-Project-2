package classes.ui.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import classes.domain.db.BibliotheekDB;
import classes.domain.db.PaginaDB;
import classes.domain.model.Boek;
import classes.domain.model.DomainException;
import classes.domain.model.Pagina;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private final BibliotheekDB db = new BibliotheekDB();
    private final PaginaDB pdb = new PaginaDB();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "index";
        if (request.getParameter("command") != null) {
            command = request.getParameter("command");
        }
        String destination;
        switch (command) {
            case "zoekForm":
                destination = zoekForm(request, response);
                break;
            case "resultaat":
                destination = resultaat(request, response);
                break;
            case "voegtoe":
                destination = voegtoe(request, response);
                break;
            case "add":
                destination = add(request, response);
                break;
            case "overzicht":
                destination = overzicht(request, response);
                break;
            case "deleteConfirmation":
                destination = deleteConfirmation(request, response);
                break;
            case "delete":
                destination = delete(request, response);
                break;
            case "wijzigen":
                destination = wijzigen(request, response);
                break;
            case "update":
                destination = update(request, response);
                break;
            case "logboek":
                destination = logboek(request, response);
                break;
            case "reset":
                destination = reset(request, response);
                break;
            default:
                destination = index(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String index(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("grootste", db.meesteBoeken());

        if (request.getSession().getAttribute("paginas") != null) {
            setSession(request, "Home");
        } else {
            ArrayList<Pagina> paginas = new ArrayList<>();
            HttpSession session = request.getSession();
            session.setAttribute("paginas", paginas);
            setSession(request, "Home");
        }

        return "index.jsp";
    }

    private String zoekForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Cookie cookie = getCookieWithKey(request, "boek");
        if (cookie != null) {
            request.setAttribute("boek", (URLDecoder.decode(cookie.getValue(), "UTF-8")));
        }

        setSession(request, "Zoekform");

        return "zoekForm.jsp";
    }

    private String resultaat(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String boek = request.getParameter("boek");

        Cookie cookie = new Cookie("boek", URLEncoder.encode(boek, "UTF-8"));
        response.addCookie(cookie);

        setSession(request, "Resultaat");

        ArrayList<String> errors = new ArrayList<>();
        if (errors.size() == 0) {
            try {
                request.setAttribute("boek", db.vind(boek));
                return "resultaat.jsp";
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);

        return zoekForm(request, response);
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response) {
        db.id();
        request.setAttribute("boeken", db.getBoeken());

        setSession(request, "Overzicht");

        return "overzicht.jsp";
    }

    private String deleteConfirmation(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        request.setAttribute("boek", db.vindId(id));

        setSession(request, "Deleteconfirmation");

        return "deleteConfirmation.jsp";
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        db.delete(id);
        return overzicht(request, response);
    }

    private String wijzigen(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        request.setAttribute("boek", db.vindId(id));

        setSession(request, "Wijzigen");

        return "wijzigen.jsp";
    }

    private String update(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        String id = request.getParameter("id");
        Boek boek = db.vindId(id);
        HttpSession session = request.getSession();
        session.setAttribute("boekwijzig", boek.getTitel());

        setTitel(boek, request, errors);

        try {
            db.checkDubbel(boek);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            boek.setTitel((String) request.getSession().getAttribute("boekwijzig"));
            request.setAttribute("errors", errors);
            return wijzigen(request, response);
        }

        setAuteur(boek, request, errors);
        setAantal(boek, request, errors);
        setGenre(boek, request, errors);
        setIsbn(boek, request, errors);
        setLeeftijd(boek, request, errors);

        if (errors.size() == 0) {
            try {
                return overzicht(request, response);
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return wijzigen(request, response);
    }

    private String voegtoe(HttpServletRequest request, HttpServletResponse response) {
        setSession(request, "Voegtoe");

        return "voegtoe.jsp";
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        Boek boek = new Boek();

        setTitel(boek, request, errors);
        setAuteur(boek, request, errors);
        setAantal(boek, request, errors);
        setGenre(boek, request, errors);
        setIsbn(boek, request, errors);
        setLeeftijd(boek, request, errors);

        if (errors.size() == 0) {
            try {
                db.voegToe(boek);
                return overzicht(request, response);
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return voegtoe(request, response);
    }

    private String logboek(HttpServletRequest request, HttpServletResponse response) {
        setSession(request, "Logboek");

        ArrayList<Pagina> log = (ArrayList<Pagina>) request.getSession().getAttribute("pagina");
        request.setAttribute("paginas", pdb.getPaginas(log));

        return "logboek.jsp";
    }

    private String reset(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<Pagina> paginas = (ArrayList<Pagina>) request.getSession().getAttribute("paginas");
        paginas.clear();
        session.setAttribute("pagina", paginas);
        return "logboek.jsp";
    }

    private void setSession(HttpServletRequest request, String curPage) {
        HttpSession session = request.getSession();
        ArrayList<Pagina> paginas = (ArrayList<Pagina>) request.getSession().getAttribute("paginas");
        if (paginas == null) {
            paginas = new ArrayList<>();
        }
        Pagina page = new Pagina();
        page.setPagina(curPage);
        page.setTijd(LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString());
        paginas.add(page);
        session.setAttribute("pagina", paginas);
    }

    private void setTitel(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String titel = request.getParameter("titel");
        boolean titelHasErrors = false;
        try {
            request.setAttribute("titelPreviousValue", titel);
            boek.setTitel(titel);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            titelHasErrors = true;
        } finally {
            request.setAttribute("titelHasErrors", titelHasErrors);
        }
    }

    private void setAuteur(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String auteur = request.getParameter("auteur");
        boolean auteurHasErrors = false;
        try {
            request.setAttribute("auteurPreviousValue", auteur);
            boek.setAuteur(auteur);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            auteurHasErrors = true;
        } finally {
            request.setAttribute("auteurHasErrors", auteurHasErrors);
        }
    }

    private void setAantal(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String aantal = request.getParameter("aantal");
        boolean aantalHasErrors = false;
        try {
            request.setAttribute("aantalPreviousValue", aantal);
            boek.setAantal(Integer.parseInt(aantal));
        } catch (NumberFormatException exc) {
            errors.add("Vul een nummer in voor aantal.");
            aantalHasErrors = true;
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            aantalHasErrors = true;
        } finally {
            request.setAttribute("aantalHasErrors", aantalHasErrors);
        }
    }

    private void setGenre(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String genre = request.getParameter("genre");
        boolean genreHasErrors = false;
        try {
            request.setAttribute("genrePreviousValue", genre);
            boek.setGenre(genre);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            genreHasErrors = true;
        } finally {
            request.setAttribute("genreHasErrors", genreHasErrors);
        }
    }

    private void setIsbn(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String isbn = request.getParameter("isbn");
        boolean isbnHasErrors = false;
        try {
            request.setAttribute("isbnPreviousValue", isbn);
            boek.setIsbn(isbn);
        } catch (NumberFormatException exc) {
            errors.add("Vul een nummer in voor isbn.");
            isbnHasErrors = true;
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            isbnHasErrors = true;
        } finally {
            request.setAttribute("isbnHasErrors", isbnHasErrors);
        }
    }

    private void setLeeftijd(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String leeftijd = request.getParameter("leeftijd");
        boolean leeftijdHasErrors = false;
        try {
            request.setAttribute("leeftijdPreviousValue", leeftijd);
            boek.setLeeftijd(Integer.parseInt(leeftijd));
        } catch (NumberFormatException exc) {
            errors.add("Vul een nummer in voor leeftijd.");
            leeftijdHasErrors = true;
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            leeftijdHasErrors = true;
        } finally {
            request.setAttribute("leeftijdHasErrors", leeftijdHasErrors);
        }
    }

    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }
}
