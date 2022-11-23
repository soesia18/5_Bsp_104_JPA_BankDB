package at.kaindorf.bank.console;

import at.kaindorf.bank.pojo.Girokonto;
import at.kaindorf.bank.pojo.Konto;
import at.kaindorf.bank.pojo.Kunde;
import at.kaindorf.bank.pojo.Sparkonto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_bankdb");
        EntityManager em = emf.createEntityManager();


        //List<Kunde> kundes = em.createQuery("SELECT k FROM Kunde k", Kunde.class).getResultList();

        List<Konto> kontoList = em.createQuery("SELECT k FROM Konto k", Konto.class).getResultList();

        System.out.println(kontoList);
        int chosenNumber;
        ObjectMapper mapper = new ObjectMapper();
        do {
            System.out.println("------------------------------------------");
            System.out.println("(1) Anzeige aller Sparkonten");
            System.out.println("(2) Anzeige aller Girokonten");
            System.out.println("(3) Anzeige aller Konten");
            System.out.println("(4) Anzeige eines Kontos");
            System.out.println("(5) Filter nach Nachname");
            System.out.println("(6) Alle Konten sortiert nach Inhaber");
            System.out.println("(8) Alle Konten sortiert nach Kontostand");
            System.out.println("(9) Quit");
            System.out.println("------------------------------------------");

            chosenNumber = new Scanner(System.in).nextInt();

            switch (chosenNumber) {
                case 1:
                    File sparFile =
                            Paths.get(System.getProperty("user.dir"),"src","main","resources","Sparkonten.json")
                                    .toFile();

                    List<Sparkonto> sparkontos =
                            kontoList
                                    .stream()
                                    .filter(konto -> konto instanceof Sparkonto)
                                    .map(konto -> (Sparkonto) konto)
                                    .collect(Collectors.toList());

                    mapper.writeValue(sparFile, sparkontos);
                    break;
                case 2:
                    File giroFile =
                            Paths.get(System.getProperty("user.dir"),"src","main","resources","Girokonten.json")
                                    .toFile();

                    List<Girokonto> girokonten =
                            kontoList
                                    .stream()
                                    .filter(konto -> konto instanceof Girokonto)
                                    .map(konto -> (Girokonto) konto)
                                    .collect(Collectors.toList());

                    mapper.writeValue(giroFile, girokonten);
                    break;
                case 3:
                    File accountFile =
                            Paths.get(System.getProperty("user.dir"),"src","main","resources","Konten.json")
                                    .toFile();
                    mapper.writeValue(accountFile, kontoList);
                    break;
                case 4:
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Account number: ");
                    int accountNumber = scan.nextInt();

                    Optional<Konto> account = kontoList
                            .stream()
                            .filter(konto -> konto.getId() == accountNumber)
                            .findFirst();

                    if (account.isPresent()) {
                        if (account.get() instanceof Girokonto) {
                            Girokonto girokonto = (Girokonto) account.get();
                            System.out.println(girokonto.getKunden() + " - " + girokonto);
                        } else {
                            Sparkonto sparkonto = (Sparkonto) account.get();
                            System.out.println(sparkonto.getKunden() + " - " + sparkonto);
                        }
                    } else {
                        System.out.println("Account with the id " + accountNumber + " is not available!");
                    }

                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }

        }while (chosenNumber != 9);


        em.close();
        emf.close();
    }
}
