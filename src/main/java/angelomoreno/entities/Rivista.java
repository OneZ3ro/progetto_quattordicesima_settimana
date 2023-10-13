package angelomoreno.entities;

import java.math.BigInteger;

public class Rivista extends Catalogo{
    private Periodicity periodicity;

    public Rivista(String titolo, int annoPubblicazione, long numeroPagine, Periodicity periodicity) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.periodicity = periodicity;
    }

    public Rivista(String titolo, int annoPubblicazione, long numeroPagine, Periodicity periodicity, BigInteger isbn) {
        this(titolo, annoPubblicazione, numeroPagine, periodicity);
        this.isbn = isbn;
    }

    public BigInteger getIsbn() {
        return this.isbn;
    }
    @Override
    public String toString() {
        return "Rivista{" +
                ", isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                "periodicity=" + periodicity +
                '}';
    }
}
