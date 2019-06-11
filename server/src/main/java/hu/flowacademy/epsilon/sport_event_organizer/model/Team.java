package hu.flowacademy.epsilon.sport_event_organizer.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
public class Team {

    @Id @GeneratedValue(generator = "team-uuid")
    @GenericGenerator(name = "team-uuid", strategy = "uuid")
    private String id;

    @Column
    private String name;

    @Column
    private String company;

    @Column
    private String imageUrl;
}
