package kassandrafalsitta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kassandrafalsitta.enums.EventType;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Football_Match")
public class FootballMatch extends Event {
    //attributi
    @Column(name = "squadra_di_casa")
    private String homeTeam;
    @Column(name = "squadra_ospite")
    private String guestTeam;
    @Column(name = "squadra_vincente")
    private String winningTeam;
    @Column(name = "numero_goal_squadra_di_casa")
    private Integer numberOfHomeTeamGoals;
    @Column(name = "numero_goal_squadra_ospite")
    private Integer numberOfGoalsAwayTeam;

    //costruttori
    public FootballMatch() {

    }

    public FootballMatch(String title, LocalDate eventDate, String description, EventType eventType, int maximumNumberOfParticipants, Location locationId, String homeTeam, String guestTeam, String winningTeam, Integer numberOfHomeTeamGoals, Integer numberOfGoalsAwayTeam) {
        super(title, eventDate, description, eventType, maximumNumberOfParticipants, locationId);
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.winningTeam = winningTeam;
        this.numberOfHomeTeamGoals = numberOfHomeTeamGoals;
        this.numberOfGoalsAwayTeam = numberOfGoalsAwayTeam;
    }

    //getter e setter
    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(String guestTeam) {
        this.guestTeam = guestTeam;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

    public Integer getNumberOfHomeTeamGoals() {
        return numberOfHomeTeamGoals;
    }

    public void setNumberOfHomeTeamGoals(Integer numberOfHomeTeamGoals) {
        this.numberOfHomeTeamGoals = numberOfHomeTeamGoals;
    }

    public Integer getNumberOfGoalsAwayTeam() {
        return numberOfGoalsAwayTeam;
    }

    public void setNumberOfGoalsAwayTeam(Integer numberOfGoalsAwayTeam) {
        this.numberOfGoalsAwayTeam = numberOfGoalsAwayTeam;
    }

    //to string
    @Override
    public String toString() {
        return "footballMatch{" +
                "homeTeam='" + homeTeam + '\'' +
                ", guestTeam='" + guestTeam + '\'' +
                ", winningTeam='" + winningTeam + '\'' +
                ", numberOfHomeTeamGoals='" + numberOfHomeTeamGoals + '\'' +
                ", numberOfGoalsAwayTeam='" + numberOfGoalsAwayTeam + '\'' +
                '}';
    }
}
