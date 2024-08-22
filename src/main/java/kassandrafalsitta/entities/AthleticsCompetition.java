package kassandrafalsitta.entities;

import jakarta.persistence.*;
import kassandrafalsitta.enums.EventType;

import java.time.LocalDate;
import java.util.Set;

@Entity
@DiscriminatorValue("Athletics_Competition")
public class AthleticsCompetition extends Event {
    //attributi
    @ManyToMany
    @JoinTable(name = "set_atleti",
            joinColumns = @JoinColumn(name = "atleti_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "person_id", nullable = false)
    )
    private Set<Person> athletesSet;

    @ManyToOne
    @JoinColumn(name = "vincitore_id", nullable = false)
    private Person winner;
    //costruttori

    public AthleticsCompetition(String title, LocalDate eventDate, String description, EventType eventType, int maximumNumberOfParticipants, Location locationId, Person winner) {
        super(title, eventDate, description, eventType, maximumNumberOfParticipants, locationId);
        this.winner = winner;
    }

    //getter e setter
    public Set<Person> getAthletesSet() {
        return athletesSet;
    }

    public void setAthletesSet(Set<Person> athletesSet) {
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
