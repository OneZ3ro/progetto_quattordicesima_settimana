package angelomoreno;

import angelomoreno.entities.Archivio;
import angelomoreno.entities.Libro;
import angelomoreno.entities.Periodicity;
import angelomoreno.entities.Rivista;
import com.github.javafaker.Faker;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Archivio archivio1 = new Archivio(createListLibri(), createListRiviste());
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Benvenuto alla biblioteca!");
            myLoop:
            while (true) {
                System.out.println("Cosa desideri fare?");
                System.out.println("[1]: Aggiungi un libro");
                System.out.println("[2]: Aggiungi una rivista");
                System.out.println("[3]: Rimuovi un libro");
                System.out.println("[4]: Rimuovi una rivista");
                System.out.println("[5]: Cerca un libro per ISBN");
                System.out.println("[6]: Cerca una rivista per ISBN");
                System.out.println("[7]: Cerca un libro per anno di pubblicazione");
                System.out.println("[8]: Cerca una rivista per anno di pubblicazione");
                System.out.println("[9]: Cerca un libro per autore");
                System.out.println("[10]: Cerca una rivista per autore");
                System.out.println("[11]: Salva sul disco l'archivio");
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
                        archivio1.getLibri().add(libro);
                        System.out.println("Hai aggiunto il tuo libro con successo!!\n");
                        break;
                    case 2:
                        System.out.println("\nCome si chiama il tuo libro?");
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
                                archivio1.getRiviste().add(new Rivista(rivistaNome, rivistaAnnoPubblicazione, rivistaPagine, Periodicity.SETTIMANALE, rivistaIsbn));
                                break;
                            case 2:
                                archivio1.getRiviste().add(new Rivista(rivistaNome, rivistaAnnoPubblicazione, rivistaPagine, Periodicity.MENSILE, rivistaIsbn));
                                break;
                            case 3:
                                archivio1.getRiviste().add(new Rivista(rivistaNome, rivistaAnnoPubblicazione, rivistaPagine, Periodicity.SEMESTRALE, rivistaIsbn));
                                break;
                        }
                        System.out.println("Hai aggiunto il tuo rivista con successo!!\n");
                        break;
                    case 3:
                        System.out.println("\nInserisci l'ISBN del libro che vuoi eliminare");
                        BigInteger removeLibroIsbn = new BigInteger(input.nextLine());
                        Map<BigInteger, List<Libro>> mapLibriToRemove = new HashMap<>(archivio1.getLibri().stream().filter(libro1 -> libro1.getIsbn().equals(removeLibroIsbn)).collect(Collectors.groupingBy(libro1 -> libro1.getIsbn())));
                        mapLibriToRemove.forEach(((isbns, libros) -> System.out.println("I libri/o che corrispondo con l'ISBN " + removeLibroIsbn + " sono/è: " + libros)));
                        System.out.println("Sei sicuro di voler eliminare questi/o libri/o?");
                        System.out.println("[1]: Si  -  [2]: No");
                        int confirmToRemoveLibri = Integer.parseInt(input.nextLine());
                        if (confirmToRemoveLibri == 1) {
//                            Iterator<List<Libro>> iter = mapLibriToRemove.values().iterator();
//                            int counter = 0;
//                            while (iter.hasNext()) {
//                                Libro current = iter.next().get(counter);
//                                if(current.getIsbn().equals(removeLibroIsbn)) {
//                                    iter.remove();
//                                }
//                                counter++;
//                            }
                            System.out.println("entrato");
                            mapLibriToRemove.remove(removeLibroIsbn);
                        } else {
                            System.out.println("Operazione annullata");
                        }
                        System.out.println("Rimossi/o libri/o!!");
                        mapLibriToRemove.forEach(((isbns, libros) -> System.out.println("Ora i libri/o che corrispondo con l'ISBN " + removeLibroIsbn + " sono/è: " + libros)));
                        break;
                    case 4:
                        System.out.println("\nHai scelto 4!!");
                        break;
                    case 5:
                        System.out.println("\nInserisci l'ISBN del libro che vuoi cercare");
                        BigInteger searchLibroIsbn = new BigInteger(input.nextLine());
                        archivio1.getLibri().stream().filter(libro1 -> libro1.getIsbn().equals(searchLibroIsbn)).collect(Collectors.groupingBy(libro1 -> libro1.getIsbn())).forEach(((isbns, libros) -> System.out.println("I libri che corrispondono all'ISBN "+ isbns + " sono: " + libros)));
                        break;
                    case 6:
                        System.out.println("\nInserisci l'ISBN della rivista che vuoi cercare");
                        BigInteger searchRivistaIsbn = new BigInteger(input.nextLine());
                        archivio1.getRiviste().stream().filter(rivista1 -> rivista1.getIsbn().equals(searchRivistaIsbn)).collect(Collectors.groupingBy(rivista1 -> rivista1.getIsbn())).forEach(((isbns, rivistas) -> System.out.println("Le riviste che corrispondono all'ISBN "+ isbns + " sono: " + rivistas)));
                        break;
                    case 7:
                        System.out.println("\nInserisci l'anno del libro che vuoi cercare");
                        int searchLibroAnno = Integer.parseInt(input.nextLine());
                        archivio1.getLibri().stream().filter(libro1 -> libro1.getAnno() == searchLibroAnno).collect(Collectors.groupingBy(libro1 -> libro1.getAnno())).forEach(((anno, libros) -> System.out.println("I libri che corrispondono all'anno "+ anno + " sono: " + libros)));
                        break;
                    case 8:
                        System.out.println("\nInserisci l'anno della rivista che vuoi cercare");
                        int searchRivistaAnno = Integer.parseInt(input.nextLine());
                        archivio1.getRiviste().stream().filter(libro1 -> libro1.getAnno() == searchRivistaAnno).collect(Collectors.groupingBy(libro1 -> libro1.getAnno())).forEach(((anno, libros) -> System.out.println("Le riviste che corrispondono all'anno "+ anno + " sono: " + libros)));
                        break;
                    case 9:
                        System.out.println("\nHai scelto 9!!");
                        break;
                    case 10:
                        System.out.println("\nHai scelto 10!!");
                        break;
                    case 11:
                        System.out.println("\nHai scelto 11!!");
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
        for (int i = 0; i < rndm.nextInt(10, 101); i++) {
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
        for (int i = 0; i < rndm.nextInt(10, 101); i++) {
            riviste.add(supplierRivista.get());
        }
        return riviste;
    }
}
