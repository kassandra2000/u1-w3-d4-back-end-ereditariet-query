package kassandrafalsitta.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import kassandrafalsitta.dao.EventDAO;
import kassandrafalsitta.dao.PersonDAO;
import kassandrafalsitta.enums.State;

import java.util.*;

@Entity
@Table(name = "partecipations")
public class Participation {
    static Faker fk = new Faker();
    static Random r = new Random();

    //attributi
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person personId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event eventId;
    @Enumerated(EnumType.STRING)
    private State state;
    //costruttore

    public Participation() {
    }

    public Participation(Person personId, Event eventId, State state) {
        this.personId = personId;
        this.eventId = eventId;
        this.state = state;
    }

    public static Participation createPartecipationRandom(EventDAO ev, PersonDAO pd, List<Event> evList, List<Person> psList) {
        State[] stateList = State.values();
        if (evList.isEmpty() || psList.isEmpty()) {
            throw new IllegalArgumentException("Le liste di eventi e persone non possono essere vuote.");
        }
        return new Participation(pd.findById(psList.get(r.nextInt(psList.size())).getPersonId()), ev.findById(evList.get(r.nextInt(evList.size())).getId()), stateList[r.nextInt(stateList.length)]);
    }

    //metodi
    public static Participation createPartecipationWithScanner(EventDAO ev, PersonDAO pd) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Inserisci l'ID della persona:");
                UUID personId = UUID.fromString(sc.nextLine());

                System.out.println("Inserisci l'ID dell'evento:");
                UUID eventId = UUID.fromString(sc.nextLine());

                System.out.println("Inserisci lo stato: CONFERMATO, DA_CONFERMARE;");
                State state = State.valueOf(sc.nextLine().toUpperCase());

                return new Participation(pd.findById(personId), ev.findById(eventId), state);

            } catch (IllegalArgumentException e) {
                System.out.println("Errore di formato: UUID o stato non valido. Riprova.");
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }


    public static List<Participation> createParticipation(EventDAO ev, PersonDAO pd, List<Event> evList, List<Person> psList) {
        List<Participation> partecipationList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int numOfParticipations;
        while (true) {
            System.out.println("quante partecipazioni vuoi creare?");
            try {
                numOfParticipations = Integer.parseInt(sc.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("inserisci un numero valido");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }

        }

        System.out.println("1. creali tu \n 2. creali random");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    for (int i = 0; i < numOfParticipations; i++) {
                        partecipationList.add(createPartecipationWithScanner(ev, pd));
                    }
                    break;
                case 2:
                    for (int i = 0; i < numOfParticipations; i++) {
                        partecipationList.add(createPartecipationRandom(ev, pd, evList, psList));
                    }
                    System.out.println("partecipazioni creati con successo");
                    break;
                default:
                    System.out.println("scelta non valida riprova!");
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("inserisci un numero valido");

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        return partecipationList;
    }

    //getter e setter

    public UUID getId() {
        return id;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    //to string

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", personId=" + personId +
                ", eventId=" + eventId +
                ", state=" + state +
                '}';
    }
}
