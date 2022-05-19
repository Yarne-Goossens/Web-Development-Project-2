package classes.ui.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.domain.db.BibliotheekDB;
import classes.domain.model.Boek;
import classes.DomainException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private final BibliotheekDB db = new BibliotheekDB();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "index";
        if (request.getParameter("command") != null) {
            command = request.getParameter("command");
        }
        String destination;
        switch (command) {
            case "index":
                destination = index(request, response);
                break;
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
            default:
                destination = index(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String index(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("grootste", db.meesteBoeken());
        return "index.jsp";
    }

    private String zoekForm(HttpServletRequest request, HttpServletResponse response) {
        return "zoekForm.jsp";
    }

    private String resultaat(HttpServletRequest request, HttpServletResponse response) {
        String boek = request.getParameter("boek");
        request.setAttribute("boek", db.vind(boek));
        return "resultaat.jsp";
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("boeken", db.getBoeken());
        return "overzicht.jsp";
    }

    private String voegtoe(HttpServletRequest request, HttpServletResponse response) {
        return "voegtoe.jsp";
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();

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
                request.setAttribute("boeken", db.getBoeken());
                return "overzicht.jsp";
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "voegtoe.jsp";
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
            request.setAttribute("naamHasErrors", titelHasErrors);
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
}
