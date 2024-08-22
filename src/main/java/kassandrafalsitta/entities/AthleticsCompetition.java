package kassandrafalsitta.entities;

import jakarta.persistence.*;
import kassandrafalsitta.enums.EventType;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("Athletics_Competition")
public class AthleticsCompetition extends Event {
    //attributi
    @ManyToMany
    @JoinTable(name = "set_atleti",
            joinColumns = @JoinColumn(name = "atleti_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "person_id", nullable = false)
    )
    private List<Person> athletesSet;

    @ManyToOne
    @JoinColumn(name = "vincitore_id", nullable = false)
    private Person winner;
    //costruttori

    public AthleticsCompetition() {

    }

    public AthleticsCompetition(String title, LocalDate eventDate, String description, EventType eventType, int maximumNumberOfParticipants, Location locationId, Person winner, List<Person> athletesSet) {
        super(title, eventDate, description, eventType, maximumNumberOfParticipants, locationId);
        this.winner = winner;
        this.athletesSet = athletesSet;
    }

    //getter e setter
    public List<Person> getAthletesSet() {
        return athletesSet;
    }

    public void setAthletesSet(List<Person> athletesSet) {
        this.athletesSet = athletesSet;
    }

    public Person getWinner() {
        return winner;
    }

    public void setWinner(Person winner) {
        this.winner = winner;
    }

    //to string
    @Override
    public String toString() {
        return "athleticsCompetition{" +
                "athletesSet=" + athletesSet +
                ", winner=" + winner +
                '}';
    }
}
