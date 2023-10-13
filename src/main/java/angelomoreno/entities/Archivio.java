package angelomoreno.entities;

import java.util.List;

public class Archivio {
    private List<Libro> libri;
    private List<Rivista> riviste;

    public Archivio(List<Libro> libri, List<Rivista> riviste) {
        this.libri = libri;
        this.riviste = riviste;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }

    public List<Rivista> getRiviste() {
        return riviste;
    }

    public void setRiviste(List<Rivista> riviste) {
        this.riviste = riviste;
    }

    @Override
    public String toString() {
        return "Archivio{" +
                "libri=" + libri +
                ", riviste=" + riviste +
                '}';
    }
}
