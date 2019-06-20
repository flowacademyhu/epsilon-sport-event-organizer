package hu.flowacademy.epsilon.sport_event_organizer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sports")
@Data
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

}
