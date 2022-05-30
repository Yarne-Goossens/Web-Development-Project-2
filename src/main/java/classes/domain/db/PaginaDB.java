package classes.domain.db;

import classes.domain.model.DomainException;
import classes.domain.model.Pagina;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PaginaDB {
    private final List<Pagina> paginalist = new ArrayList<>();

    public PaginaDB() {
        this.voegToe(new Pagina("Test", LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString()));
    }

    public ArrayList getPaginas(ArrayList paginas) {
        return paginas;
    }

    public void voegToe(Pagina pagina) {
        if (pagina == null)
            throw new DomainException("De pagina die je toevoegde is ongeldig");
        paginalist.add(pagina);
    }
}
