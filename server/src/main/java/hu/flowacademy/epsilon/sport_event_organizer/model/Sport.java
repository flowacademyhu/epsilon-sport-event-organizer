package hu.flowacademy.epsilon.sport_event_organizer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sports")
public class Sport {

    @Id
    @Column(unique = true)
    private String name;

    @Column
    private Integer breakCounter;

    @Column
    private Integer breakDurationInMinutes;

    @Column
    private Integer matchDurationInMinutes;

    @OneToMany(mappedBy = "sport")
    private Set<Cup> cups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBreakCounter() {
        return breakCounter;
    }

    public void setBreakCounter(Integer breakCounter) {
        this.breakCounter = breakCounter;
    }

    public Integer getBreakDurationInMinutes() {
        return breakDurationInMinutes;
    }

    public void setBreakDurationInMinutes(Integer breakDurationInMinutes) {
        this.breakDurationInMinutes = breakDurationInMinutes;
    }

    public Set<Cup> getCups() {
        return cups;
    }

    public void setCups(Set<Cup> cups) {
        this.cups = cups;
    }
}
