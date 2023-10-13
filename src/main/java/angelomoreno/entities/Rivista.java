package angelomoreno.entities;

public class Rivista extends Catalogo{
    private Periodicity periodicity;

    public Rivista(String titolo, int annoPubblicazione, long numeroPagine, Periodicity periodicity) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.periodicity = periodicity;
    }
}
