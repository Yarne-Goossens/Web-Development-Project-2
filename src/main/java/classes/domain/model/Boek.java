package classes.domain.model;

public class Boek {
    private String titel;
    private String auteur;
    private int aantal;
    private String genre;
    private String isbn;
    private int leeftijd;
    private int id;

    public Boek() {
    }

    public Boek(String titel, String auteur, int aantal, String genre, String isbn, int leeftijd, int id) {
        this.setTitel(titel);
        this.setAuteur(auteur);
        this.setAantal(aantal);
        this.setGenre(genre);
        this.setIsbn(isbn);
        this.setLeeftijd(leeftijd);
        this.setId(id);
    }

    public void setTitel(String titel) {
        if (titel == null || titel.isEmpty()) {
            throw new DomainException("Vul een correcte titel in.");
        }
        this.titel = titel;
    }

    public void setAuteur(String auteur) {
        if (auteur == null || auteur.isEmpty()) {
            throw new DomainException("Vul een correcte naam in.");
        }
        this.auteur = auteur;
    }

    public void setAantal(int aantal) {
        if (aantal < 0) {
            throw new DomainException("Vul een correct aantal in.");
        }
        this.aantal = aantal;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new DomainException("Vul een correct genre in.");
        }
        this.genre = genre;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty() || !isbn.matches("[0-9]+") || isbn.length() != 13) {
            throw new DomainException("Vul een correct ISBN-nummer in. (Een isbn nummer moet 13 cijfers lang zijn.)");
        }
        this.isbn = isbn;
    }

    public void setLeeftijd(int leeftijd) {
        if (leeftijd < 0) {
            throw new DomainException("Vul een correcte leeftijd in.");
        }
        this.leeftijd = leeftijd;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new DomainException("Id mag niet minder dan 0 zijn.");
        }
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAantal() {
        return aantal;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public int getId() {
        return id;
    }
}
