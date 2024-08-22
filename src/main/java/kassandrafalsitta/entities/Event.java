package kassandrafalsitta.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import kassandrafalsitta.dao.LocationDAO;
import kassandrafalsitta.enums.EventType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Entity
@Table(name = "events")
public class Event {
    static Faker fk = new Faker();

    ;
    static Random r = new Random();
    //attributi
    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private UUID id;
    private String title;

    ;
    @Column(name = "event_date")
    private LocalDate eventDate;
    private String description;
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Column(name = "num_max_participants")
    private int maximumNumberOfParticipants;
    @OneToMany(mappedBy = "eventId")
    private List<Participation> partecipationList;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location locationId;
    //costruttore
    public Event() {
    }
    public Event(String title, LocalDate eventDate, String description, EventType eventType, int maximumNumberOfParticipants, Location locationId) {
        this.title = title;
        this.eventDate = eventDate;
        this.description = description;
        this.eventType = eventType;
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
        this.locationId = locationId;
    }

    //supplier
    public static Event eventSupplierWithScanner(LocationDAO ld) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("inserisci titolo");
                String title = sc.nextLine();
                System.out.println("inserisci data");
                LocalDate date = LocalDate.parse(sc.nextLine());
                System.out.println("inserisci descrizione");
                String description = sc.nextLine();
                System.out.println("inserisci tipo: PUBBLICO o PRIVATO");
                EventType type = EventType.valueOf(sc.nextLine());
                System.out.println("inserisci numero massimo di partecipanti");
                int nMaxPartecipants = Integer.parseInt(sc.nextLine());
                System.out.println("inserisci l'id della location");
                UUID locationId = UUID.fromString(sc.nextLine());

                return new Event(title, date, description, type, nMaxPartecipants, ld.findById(locationId));

            } catch (InputMismatchException e) {
                System.out.println("inserisci i valori correttamente");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public static Event eventSupplier(List<Location> locationList) {
        EventType[] eventTypeList = EventType.values();
        LocalDate date = fk.date().birthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return new Event(fk.esports().event(), date, fk.lorem().fixedString(50), eventTypeList[r.nextInt(eventTypeList.length)], r.nextInt(1, 40), locationList.get(r.nextInt(locationList.size())));
    }

    //metodi
    public static List<Event> createEvent(LocationDAO ld, List<Location> locationList) {
        List<Event> eventList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int numOfEvents;
        while (true) {
            System.out.println("quanti eventi vuoi creare?");
            try {
                numOfEvents = Integer.parseInt(sc.nextLine());
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
                    for (int i = 0; i < numOfEvents; i++) {
                        eventList.add(eventSupplierWithScanner(ld));
                    }
                    break;
                case 2:
                    for (int i = 0; i < numOfEvents; i++) {
                        eventList.add(eventSupplier(locationList));
                    }
                    System.out.println("eventi creati con successo");
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

        return eventList;
    }

    //getter e setter

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getMaximumNumberOfParticipants() {
        return maximumNumberOfParticipants;
    }

    public void setMaximumNumberOfParticipants(int maximumNumberOfParticipants) {
        this.maximumNumberOfParticipants = maximumNumberOfParticipants;
    }

    //to string

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title='" + title + '\'' + ", eventDate=" + eventDate + ", description='" + description + '\'' + ", eventType=" + eventType + ", maximumNumberOfParticipants='" + maximumNumberOfParticipants + '\'' + '}';
    }
}
