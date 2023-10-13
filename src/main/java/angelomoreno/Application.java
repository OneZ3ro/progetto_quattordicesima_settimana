package angelomoreno;

import angelomoreno.entities.Archivio;
import angelomoreno.entities.Libro;
import angelomoreno.entities.Periodicity;
import angelomoreno.entities.Rivista;
import com.github.javafaker.Faker;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Supplier;

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
                System.out.println("[7]: Cerca un libro per autore");
                System.out.println("[8]: Cerca una rivista per autore");
                System.out.println("[9]: Salva sul disco l'archivio");
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
                        System.out.println("\nHai scelto 2!!");
                        break;
                    case 3:
                        System.out.println("\nHai scelto 3!!");
                        break;
                    case 4:
                        System.out.println("\nHai scelto 4!!");
                        break;
                    case 5:
                        System.out.println("\nHai scelto 5!!");
                        break;
                    case 6:
                        System.out.println("\nHai scelto 6!!");
                        break;
                    case 7:
                        System.out.println("\nHai scelto 7!!");
                        break;
                    case 8:
                        System.out.println("\nHai scelto 8!!");
                        break;
                    case 9:
                        System.out.println("\nHai scelto 9!!");
                        break;
                    default:
                        System.err.println("Non hai scelto una delle possibili scelte. Riprova");
                        break;
                }
            }
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Errore: probabilmente hai inserito una stringa (parola) invece che un intero (numero). Riprova");
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
