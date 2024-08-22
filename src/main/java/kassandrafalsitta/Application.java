package kassandrafalsitta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import kassandrafalsitta.dao.EventDAO;
import kassandrafalsitta.dao.LocationDAO;
import kassandrafalsitta.dao.PartecipationDAO;
import kassandrafalsitta.dao.PersonDAO;
import kassandrafalsitta.entities.Event;
import kassandrafalsitta.entities.Location;
import kassandrafalsitta.entities.Participation;
import kassandrafalsitta.entities.Person;
import kassandrafalsitta.enums.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1w3d4");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        //dao
        EventDAO ev = new EventDAO(em);
        LocationDAO ld = new LocationDAO(em);
        PartecipationDAO pd = new PartecipationDAO(em);
        PersonDAO prsd = new PersonDAO(em);
        //liste
        List<Event> events = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        List<Participation> partecipations = new ArrayList<>();

//        Person person = new Person("gigo", "soretti", "gigo@gmail.com", LocalDate.parse("2000-12-11"), Gender.M);
//        persons.add(person);
//        prsd.save(persons);

//        while (true) {
//            try {
//                System.out.println("vuoi creare un evento?");
////               "1. Crea evento \n2. Salva evento \n
//
//                System.out.println("1. Crea evento \n2. Salva evento  \n3. Cerca evento \n4. Elimina evento\n5. Crea persona\n6. Salva persona\n7. Crea location\n8. Salva location\n9. Crea partecipazione\n10. Salva partecipazione\n11. Esci");
//                String choice = sc.nextLine();
//                switch (choice) {
////
//                    case "1":
//                        locations = em.createQuery("SELECT e FROM Location e", Location.class).getResultList();
//                        persons = em.createQuery("SELECT e FROM Person e", Person.class).getResultList();
//                        events = createEvent(ld, locations, persons);
//                        System.out.println(events);
//                        break;
//                    case "2":
//                        ev.save(events);
//                        break;
//                    case "3":
//                        try {
//                            System.out.println("Quale evento vuoi cercare tramite id?");
//                            UUID findId = UUID.fromString(sc.nextLine());
//                            ev.findById(findId);
//
//                        } catch (NumberFormatException e) {
//                            System.out.println("Inserisci il formato corretto\n");
//
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
//
//                        }
//                        break;
//                    case "4":
//                        try {
//                            System.out.println("Quale evento vuoi eliminare tramite id?");
//                            UUID findByIdAndDelete = UUID.fromString(sc.nextLine());
//                            ev.findByIdAndDelete(findByIdAndDelete);
//
//                        } catch (NumberFormatException e) {
//                            System.out.println("Inserisci il formato corretto");
//
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
//
//                        }
//                        break;
//                    case "5":
//                        persons = createPerson();
//                        System.out.println(persons);
//                        break;
//                    case "6":
//                        prsd.save(persons);
//                        break;
//                    case "7":
//                        locations = createLocation();
//                        System.out.println(locations);
//                        break;
//                    case "8":
//                        ld.save(locations);
//                        break;
//                    case "9":
//                        events = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
//                        persons = em.createQuery("SELECT e FROM Person e", Person.class).getResultList();
//                        partecipations = createParticipation(ev, prsd, events, persons);
//                        System.out.println(partecipations);
//                        break;
//                    case "10":
//                        pd.save(partecipations);
//                        break;
//                    case "11":
//                        break;
//                    default:
//                        System.out.println("il valore non è valido");
//                        break;
//                }
//                if (choice.equals("11")) {
//                    System.out.println("Uscita dal programma...");
//                    break;
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }

        //-----------------------------------------esercizio 1-------------------------------------------------
        System.out.println("------------cerco per genere--------------------");
        System.out.println(ev.getConcertByGenre(Genre.POP));
        System.out.println("------------cerco in base allo streaming true/false--------------------");
        System.out.println(ev.getConcertInStreaming(true));
        //-----------------------------------------esercizio 2-------------------------------------------------
        System.out.println("------------ottenere partite vinte in casa--------------------");
        System.out.println(ev.getPartiteVinteInCasa());
        System.out.println("------------ottenere partite vinte in trasferta--------------------");
        System.out.println(ev.getPartiteVinteInTrasferta());

        em.close();
        emf.close();
    }


}
