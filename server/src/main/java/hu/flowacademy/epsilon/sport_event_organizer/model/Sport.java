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

    @OneToMany(mappedBy = "sports")
    private Set<Cup> cups;


}
