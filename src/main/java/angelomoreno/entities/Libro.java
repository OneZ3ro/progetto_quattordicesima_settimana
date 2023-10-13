package angelomoreno.entities;

public class Libro extends Catalogo{
    private String autore;
    private String genere;

    public Libro(String titolo, int annoPubblicazione, long numeroPagine, String autore, String genere) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }
}
