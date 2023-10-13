package angelomoreno.entities;

import java.math.BigInteger;
import java.util.Random;

public abstract class Catalogo {
    protected BigInteger isbn;
    protected String titolo;
    protected int annoPubblicazione;
    protected long numeroPagine;

    public Catalogo(String titolo, int annoPubblicazione, long numeroPagine) {
        Random rndm = new Random();
        String c1 = String.valueOf(rndm.nextInt(0, 10));
        String c2 = String.valueOf(rndm.nextInt(0, 10));
        String c3 = String.valueOf(rndm.nextInt(0, 10));
        String c4 = String.valueOf(rndm.nextInt(0, 10));
        String c5 = String.valueOf(rndm.nextInt(0, 10));
        String c6 = String.valueOf(rndm.nextInt(0, 10));
        String c7 = String.valueOf(rndm.nextInt(0, 10));
        String c8 = String.valueOf(rndm.nextInt(0, 10));
        String c9 = String.valueOf(rndm.nextInt(0, 10));
        String c10 = String.valueOf(rndm.nextInt(0, 10));
        String c11 = String.valueOf(rndm.nextInt(0, 10));
        String c12 = String.valueOf(rndm.nextInt(0, 10));
        this.isbn = new BigInteger("9" + c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9 + c10 + c11 + c12);
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

}
