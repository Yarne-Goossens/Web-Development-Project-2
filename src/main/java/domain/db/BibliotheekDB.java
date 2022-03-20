package domain.db;

import domain.model.Boek;

import java.util.ArrayList;
import java.util.List;

public class BibliotheekDB {
    private final List<Boek> boeklist = new ArrayList<>();

    public BibliotheekDB() {
        this.voegToe(new Boek("The Lord of the Rings", "J.R.R. Tolkien", 5, "Fantasie", "9780261103252", 15));
        this.voegToe(new Boek("The Maze Runner", "James Dashner", 7, "Avontuur", "9781908435132", 15));
        this.voegToe(new Boek("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 10, "Fantasie", "9789076174082", 12));
    }

    public ArrayList getBoeken(){return (ArrayList<Boek>) this.boeklist;}

    public void voegToe(Boek boek) {
        boeklist.add(boek);
    }

    public Boek meesteBoeken(){
        Boek aantal = boeklist.get(0);
        for(Boek i : boeklist){
            if(i.getAantal() > aantal.getAantal()){
                aantal = i;
            }
        }
        return aantal;
    }
}
