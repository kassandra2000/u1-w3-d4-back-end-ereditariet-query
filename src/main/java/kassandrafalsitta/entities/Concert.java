package kassandrafalsitta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kassandrafalsitta.enums.EventType;
import kassandrafalsitta.enums.Genre;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Concert")
public class Concert extends Event {

    //attributi
    @Column(name = "genere")
    Genre genre;
    @Column(name = "in_streaming")
    Boolean inStreaming;

    //costruttori
    public Concert() {

    }

    public Concert(String title, LocalDate eventDate, String description, EventType eventType, int maximumNumberOfParticipants, Location locationId, Genre genre, Boolean inStreaming) {
        super(title, eventDate, description, eventType, maximumNumberOfParticipants, locationId);
        this.genre = genre;
        this.inStreaming = inStreaming;
    }

    //getter e setter

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Boolean getInStreaming() {
        return inStreaming;
    }

    public void setInStreaming(Boolean inStreaming) {
        this.inStreaming = inStreaming;
    }

    //to string

    @Override
    public String toString() {
        return "Concert{" +
                "genre=" + genre +
                ", inStreaming=" + inStreaming +
                '}';
    }
}
