package angelomoreno;

import angelomoreno.entities.*;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        List<Libro> suppLibri = new ArrayList<>(createListLibri());
        List<Rivista> suppRiviste = new ArrayList<>(createListRiviste());
        List<Catalogo> catalogos = new ArrayList<>();
        catalogos.addAll(suppLibri);
        catalogos.addAll((suppRiviste));

        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Benvenuto alla biblioteca!");
            myLoop:
            while (true) {
                System.out.println("Cosa desideri fare?");
                System.out.println("[1]: Aggiungi un libro");
                System.out.println("[2]: Aggiungi una rivista");
                System.out.println("[3]: Rimuovi un libro o una rivista");
                System.out.println("[4]: Cerca un libro o rivista per ISBN");
                System.out.println("[5]: Cerca un libro o rivista per anno di pubblicazione");
                System.out.println("[6]: Cerca un libro per autore");
                System.out.println("[7]: Salva sul disco l'archivio");
                System.out.println("[0]: Termina programma");

                int choose1 = Integer.parseInt(input.nextLine());
                switch (choose1) {
                    case 0:
                        System.out.println("Programma terminato con successo!!");
                        break myLoop;
                    case 1:
                        System.out.println("\nCome si chiama il tuo libro?");
                        String libroNome = input.nextLine();
                        System.out.println("In che hanno è stato pubblicato? (Inserisci solo l'anno)");
                        int libroAnnoPubblicazione = Integer.parseInt(input.nextLine());
                        System.out.println("Quante pagine ha il tuo libro?");
                        long libroPagine = Long.parseLong(input.nextLine());
                        System.out.println("Chi è l'autore?");
                        String libroAutore = input.nextLine();
                        System.out.println("Che genere è il tuo libro?");
                        String libroGenere = input.nextLine();
                        System.out.println("Inserisci l'ISBN del libro");
                        BigInteger libroIsbn = new BigInteger(input.nextLine());
                        Libro libro = new Libro(libroNome, libroAnnoPubblicazione, libroPagine, libroAutore, libroGenere, libroIsbn);
                        catalogos.add(libro);
                        System.out.println("Hai aggiunto il tuo libro con successo!!\n");
                        break;
                    case 2:
                        System.out.println("\nCome si chiama la tua rivista?");
                        String rivistaNome = input.nextLine();
                        System.out.println("In che hanno è stato pubblicato? (Inserisci solo l'anno)");
                        int rivistaAnnoPubblicazione = Integer.parseInt(input.nextLine());
                        System.out.println("Quante pagine ha il tuo rivista?");
                        long rivistaPagine = Long.parseLong(input.nextLine());
                        System.out.println("Inserisci l'ISBN del rivista");
                        BigInteger rivistaIsbn = new BigInteger(input.nextLine());
                        System.out.println("Ogni quanto è pubblicato? (Scegli un numero)");
                        System.out.println("[1]: SETTIMANALMENTE  -  [2]: MENSILMENTE  -  [3]: SEMESTRALMENTE");
                        int choosePeriodicity = Integer.parseInt(input.nextLine());
                        switch (choosePeriodicity) {
                            case 1:
                                catalogos.add(new Rivista(rivistaNome, rivistaAnnoPubblicazione, rivistaPagine, Periodicity.SETTIMANALE, rivistaIsbn));
                                break;
                            case 2:
                                catalogos.add(new Rivista(rivistaNome, rivistaAnnoPubblicazione, rivistaPagine, Periodicity.MENSILE, rivistaIsbn));
                                break;
                            case 3:
                                catalogos.add(new Rivista(rivistaNome, rivistaAnnoPubblicazione, rivistaPagine, Periodicity.SEMESTRALE, rivistaIsbn));
                                break;
                        }
                        System.out.println("Hai aggiunto il tuo rivista con successo!!\n");
                        break;
                    case 3:
                        System.out.println("Inserisci l'ISBN del libro che vuoi eliminare");
                        BigInteger removeLibroIsbn = new BigInteger(input.nextLine());
                        Iterator<Catalogo> iter = catalogos.iterator();
                        while(iter.hasNext()){
                            Catalogo current = iter.next();
                            if (current.getIsbn().equals(removeLibroIsbn)){
                                iter.remove();
                            }
                        }
                        System.out.println("Cancellazione avvenuta!!");
                        break;
                    case 4:
                        System.out.println("\nInserisci l'ISBN del libro o rivista che vuoi cercare");
                        BigInteger searchLibroIsbn = new BigInteger(input.nextLine());
                        catalogos.stream().filter(libro1 -> libro1.getIsbn().equals(searchLibroIsbn)).collect(Collectors.groupingBy(libro1 -> libro1.getIsbn())).forEach(((isbns, libros) -> System.out.println("I libri o riviste che corrispondono all'ISBN "+ isbns + " sono: " + libros)));
                        break;
                    case 5:
                        System.out.println("\nInserisci l'anno del libro o rivista che vuoi cercare");
                        int searchLibroAnno = Integer.parseInt(input.nextLine());
                        catalogos.stream().filter(libro1 -> libro1.getAnnoPubblicazione() == searchLibroAnno).collect(Collectors.groupingBy(libro1 -> libro1.getAnnoPubblicazione())).forEach(((anno, libros) -> System.out.println("I libri o riviste che corrispondono all'anno "+ anno + " sono: " + libros)));
                        break;
                    case 6:
                        System.out.println("\nInserisci l'autore del libro che vuoi cercare");
                        String searchLibroAutore = input.nextLine();
                        List<Libro> app = new ArrayList<>();
                        for (int i = 0; i < catalogos.size(); i++) {
                            if (catalogos.get(i) instanceof Libro) {
                                app.add((Libro) catalogos.get(i));
                            }
                        }
                        app.stream().filter(libro1 -> libro1.getAutore().equals(searchLibroAutore)).collect(Collectors.groupingBy(libro1 -> libro1.getAutore())).forEach(((autore, libros) -> System.out.println("I libri che corrispondono all'autore "+ autore + " sono: " + libros)));
                        break;
                    case 7:
                        try {
                            creaFile(catalogos);
                        } catch (IOException ioException) {
                            ioException.getMessage();
                        }

                        break;
                    default:
                        System.err.println("Non hai scelto una delle possibili scelte. Riprova");
                        break;
                }
            }
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Errore: probabilmente hai inserito una stringa (parola) invece che un intero (numero). Riprova");
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            input.close();
        }
    }

    public static List<Libro> createListLibri() {
        List<Libro> libri = new ArrayList<>();
        Supplier<Libro> supplierLibro = () -> {
            Faker faker = new Faker(Locale.ITALY);
            Random rndm = new Random();
            return new Libro(faker.book().title(), rndm.nextInt(1800, 2024), rndm.nextLong(10, 1200), faker.book().author(), faker.book().genre());
        };
        Random rndm = new Random();
        for (int i = 0; i < rndm.nextInt(100, 251); i++) {
            libri.add(supplierLibro.get());
        }
        return libri;
    }

    public static List<Rivista> createListRiviste() {
        List<Rivista> riviste = new ArrayList<>();
        Supplier<Rivista> supplierRivista = () -> {
            Faker faker = new Faker(Locale.ITALY);
            Random rndm = new Random();
            int num = rndm.nextInt(1, 4);
            if (num == 1) {
                return new Rivista(faker.book().title(), rndm.nextInt(1800, 2024), rndm.nextLong(10, 1200), Periodicity.SETTIMANALE);
            } else if(num == 2) {
                return new Rivista(faker.book().title(), rndm.nextInt(1800, 2024), rndm.nextLong(10, 1200), Periodicity.MENSILE);
            } else {
                return new Rivista(faker.book().title(), rndm.nextInt(1800, 2024), rndm.nextLong(10, 1200), Periodicity.SEMESTRALE);
            }
        };
        Random rndm = new Random();
        for (int i = 0; i < rndm.nextInt(100, 251); i++) {
            riviste.add(supplierRivista.get());
        }
        return riviste;
    }

    public static void creaFile(List<Catalogo> catalogos) throws IOException {
        File file = new File("src/output.txt");
        FileUtils.writeStringToFile(file, "", StandardCharsets.UTF_8);
        List<Libro> appLibri = new ArrayList<>();
        List<Rivista> appRivista = new ArrayList<>();
        for (int i = 0; i < catalogos.size(); i++) {
            if (catalogos.get(i) instanceof Libro) {
                appLibri.add((Libro) catalogos.get(i));
            } else {
                appRivista.add((Rivista) catalogos.get(i));
            }
        }
        for (int i = 0; i < appLibri.size(); i++) {
            FileUtils.writeStringToFile(file, appLibri.get(i).getIsbn().toString() + "@" + appLibri.get(i).getTitolo() + "@" + appLibri.get(i).getAnno() + "@" + appLibri.get(i).getNumeroPagine() + "@" + appLibri.get(i).getAutore() + "@" + appLibri.get(i).getGenere() + "#" + System.lineSeparator(), StandardCharsets.UTF_8, true);
        }
        FileUtils.writeStringToFile(file, "," + System.lineSeparator(), StandardCharsets.UTF_8, true);
        for (int i = 0; i < appRivista.size(); i++) {
            FileUtils.writeStringToFile(file, appRivista.get(i).getIsbn().toString() + "@" + appRivista.get(i).getTitolo() + "@" + appRivista.get(i).getAnno() + "@" + appRivista.get(i).getNumeroPagine() + "@" + appRivista.get(i).getPeriodicity() + "#" + System.lineSeparator(), StandardCharsets.UTF_8, true);
        }
        String contenuto = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        System.out.println("Hai creato con successo il file contenente l'archivio.\nContenuto del file:");
        System.out.println(contenuto);
    }


}
