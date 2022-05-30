package classes.domain.model;

public class Pagina {
    private String pagina;
    private String tijd;

    public Pagina() {

    }

    public Pagina(String pagina, String tijd) {
        this.pagina = pagina;
        this.tijd = tijd;
    }

    public void setPagina(String pagina) {
        if (pagina == null || pagina.isEmpty()) {
            throw new IllegalArgumentException("Vul een correcte pagina in.");
        }
        this.pagina = pagina;
    }

    public void setTijd(String tijd) {
        this.tijd = tijd;
    }

    public String getPagina() {
        return pagina;
    }

    public String getTijd() {
        return tijd;
    }
}
