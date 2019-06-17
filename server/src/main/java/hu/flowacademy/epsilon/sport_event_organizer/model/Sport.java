package hu.flowacademy.epsilon.sport_event_organizer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
