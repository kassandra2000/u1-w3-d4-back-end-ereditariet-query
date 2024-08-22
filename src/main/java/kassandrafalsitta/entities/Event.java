package kassandrafalsitta.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import kassandrafalsitta.dao.LocationDAO;
import kassandrafalsitta.enums.EventType;
import kassandrafalsitta.enums.Genre;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Entity
@Table(name = "events")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_evento")

@NamedQuery(name = "findPartiteVinteInCasa", query = "SELECT f FROM FootballMatch f WHERE f.homeTeam=f.winningTeam")
@NamedQuery(name = "findPartiteVinteInTrasferta", query = "SELECT f FROM FootballMatch f WHERE f.guestTeam=f.winningTeam")
public abstract class Event {
    static Faker fk = new Faker();

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

    //metodi
    public static List<Event> createEvent(LocationDAO ld, List<Location> locationList, List<Person> personList) {
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

        System.out.println("1. Crea partita di calcio \n 2. Crea concerto\n 3. Crea competizione di atletica");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    for (int i = 0; i < numOfEvents; i++) {
                        eventList.add(footballMatchCreateOne(locationList));
                    }
                    System.out.println("Partita di calcio creato con successo");
                    break;
                case 2:
                    for (int i = 0; i < numOfEvents; i++) {
                        eventList.add(concertCreateOne(locationList));
                    }
                    System.out.println("Concerto creato con successo");
                    break;
                case 3:
                    for (int i = 0; i < numOfEvents; i++) {
                        eventList.add(athleticsCompetitionCreateOne(personList, locationList));
                    }
                    System.out.println("Gara di atletica creata con successo");
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

    public static Concert concertCreateOne(List<Location> locationList) {
        EventType[] eventTypeList = EventType.values();
        Genre[] genreList = Genre.values();

        LocalDate date = fk.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new Concert(fk.esports().event(), date, fk.lorem().fixedString(50), eventTypeList[r.nextInt(eventTypeList.length)], r.nextInt(1, 40), locationList.get(r.nextInt(locationList.size())), genreList[r.nextInt(genreList.length)], r.nextInt(0, 2) == 1);
    }

    public static AthleticsCompetition athleticsCompetitionCreateOne(List<Person> personList, List<Location> locationList) {
        EventType[] eventTypeList = EventType.values();
        LocalDate date = fk.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new AthleticsCompetition(fk.esports().event(), date, fk.lorem().fixedString(50), eventTypeList[r.nextInt(eventTypeList.length)], r.nextInt(1, 40), locationList.get(r.nextInt(locationList.size())), personList.get(r.nextInt(personList.size())));
    }

    public static FootballMatch footballMatchCreateOne(List<Location> locationList) {
        EventType[] eventTypeList = EventType.values();
        LocalDate date = fk.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new FootballMatch(fk.esports().event(), date, fk.lorem().fixedString(50), eventTypeList[r.nextInt(eventTypeList.length)], r.nextInt(1, 40), locationList.get(r.nextInt(locationList.size())), fk.esports().team(), fk.esports().team(), fk.esports().team(), r.nextInt(0, 5), r.nextInt(0, 5));
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
