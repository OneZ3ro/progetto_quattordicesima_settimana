package angelomoreno.entities;

import java.math.BigInteger;

public class Libro extends Catalogo{
    private String autore;
    private String genere;

    public Libro(String titolo, int annoPubblicazione, long numeroPagine, String autore, String genere) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro(String titolo, int annoPubblicazione, long numeroPagine, String autore, String genere, BigInteger isbn) {
        this(titolo, annoPubblicazione, numeroPagine, autore, genere);
        this.isbn = isbn;
    }

    public BigInteger getIsbn() {
        return this.isbn;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public long getNumeroPagine() {
        return this.numeroPagine;
    }

    public int getAnno() {
        return this.annoPubblicazione;
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }
}
