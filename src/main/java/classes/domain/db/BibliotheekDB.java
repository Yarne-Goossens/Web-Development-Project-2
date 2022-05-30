package classes.domain.db;

import classes.domain.model.DomainException;
import classes.domain.model.Boek;

import java.util.ArrayList;
import java.util.List;

public class BibliotheekDB {
    private final List<Boek> boeklist = new ArrayList<>();

    public BibliotheekDB() {
        this.voegToe(new Boek("The Lord of the Rings", "J.R.R. Tolkien", 5, "Fantasie", "9780261103252", 15, 0));
        this.voegToe(new Boek("The Maze Runner", "James Dashner", 7, "Avontuur", "9781908435132", 15, 0));
        this.voegToe(new Boek("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 10, "Fantasie", "9789076174082", 12, 0));
    }

    public ArrayList getBoeken() {
        return (ArrayList<Boek>) this.boeklist;
    }

    public void voegToe(Boek boek) {
        if (boek == null)
            throw new DomainException("Het boek dat je toevoegde is ongeldig");
        if (vind(boek.getTitel()) != null)
            throw new DomainException("Je mag een boek maar één keer toevoegen");
        boeklist.add(boek);
    }

    public Boek meesteBoeken() {
        Boek aantal = boeklist.get(0);
        for (Boek i : boeklist) {
            if (i.getAantal() > aantal.getAantal()) {
                aantal = i;
            }
        }
        return aantal;
    }

    public Boek vind(String boek) {
        if (boek == null || boek.isEmpty())
            throw new DomainException("Het veld mag niet leeg zijn");
        for (Boek b : boeklist) {
            if (b.getTitel().equalsIgnoreCase(boek))
                return b;
        }
        return null;
    }

    public void id() {
        int id = 0;
        for (Boek i : boeklist) {
            i.setId(id);
            id++;
        }
    }

    public void delete(String id) {
        int boekid = Integer.parseInt(id);
        boeklist.removeIf(b -> b.getId() == boekid);
    }

    public Boek vindId(String id) {
        int boekid = Integer.parseInt(id);
        for (Boek b : boeklist) {
            if (b.getId() == boekid) {
                return b;
            }
        }
        return null;
    }

    public void checkDubbel(Boek boek) {
        if (boek == null)
            throw new DomainException("Het boek dat je toevoegde is ongeldig");
        for (Boek b : boeklist) {
            if (boek.getTitel().equalsIgnoreCase(b.getTitel()) && b.getId() != boek.getId()) {
                throw new DomainException("Je mag een boek maar één keer toevoegen");
            }
        }
    }
}

