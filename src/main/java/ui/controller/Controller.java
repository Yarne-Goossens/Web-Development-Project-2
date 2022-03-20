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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }
}
