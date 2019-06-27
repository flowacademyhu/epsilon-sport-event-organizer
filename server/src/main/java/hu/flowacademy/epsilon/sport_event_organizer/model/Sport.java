package hu.flowacademy.epsilon.sport_event_organizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sports")
@Data
@EqualsAndHashCode(exclude = {"cups"})
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

    @JsonIgnore
    @OneToMany(mappedBy = "sport")
    private Set<Cup> cups;

}
