package angelomoreno.entities;

import java.util.Random;

public abstract class Catalogo {
    private long isbn;
    private String titolo;
    private int annoPubblicazione;
    private long numeroPagine;

    public Catalogo(String titolo, int annoPubblicazione, long numeroPagine) {
        Random rndm = new Random();
        this.isbn = rndm.nextLong(90000000, 99999999);
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }
}
