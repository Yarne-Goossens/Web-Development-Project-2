package ui.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.BibliotheekDB;
import domain.model.Boek;


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
                case "confirmation":
                destination=confirmation(request,response);
                break;
            case "zoek":
                destination=zoek(request,response);
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

    private String zoek(HttpServletRequest request, HttpServletResponse response) {
        String boek = request.getParameter("boek");
        request.setAttribute("boek",db.vind(boek));
        return "resultaat.jsp";
    }

    private String confirmation(HttpServletRequest request, HttpServletResponse response) {
        return "confirmation.jsp";
    }

    private String voegtoe(HttpServletRequest request, HttpServletResponse response) {
        return "voegtoe.jsp";
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        String titel = request.getParameter("titel");
        String auteur = request.getParameter("auteur");
        String aantalFromParameter = request.getParameter("aantal");
        String genre = request.getParameter("genre");
        String isbn = request.getParameter("isbn");
        String leeftijdFromParameter = request.getParameter("leeftijd");
        int aantal = Integer.parseInt(aantalFromParameter);
        int leeftijd = Integer.parseInt(leeftijdFromParameter);

        db.voegToe(new Boek(titel, auteur, aantal, genre, isbn, leeftijd));
        request.setAttribute("boeken", db.getBoeken());

        return "overzicht.jsp";
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("boeken", db.getBoeken());
        return "overzicht.jsp";
    }

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("boeken", db.getBoeken());
        request.setAttribute("meeste",db.meesteBoeken());
        RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titel = request.getParameter("titel");
        String auteur = request.getParameter("auteur");
        String aantalFromParameter = request.getParameter("aantal");
        String genre = request.getParameter("genre");
        String isbn = request.getParameter("isbn");
        String leeftijdFromParameter = request.getParameter("leeftijd");
        int aantal = Integer.parseInt(aantalFromParameter);
        int leeftijd = Integer.parseInt(leeftijdFromParameter);

        db.voegToe(new Boek(titel, auteur, aantal, genre, isbn, leeftijd));

        request.setAttribute("boeken", db.getBoeken());
        request.setAttribute("meeste",db.meesteBoeken());

        RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
        view.forward(request, response);
    }*/
}
