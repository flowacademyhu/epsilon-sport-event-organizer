package hu.flowacademy.epsilon.sport_event_organizer.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cups")
@Data
public class Cup {

    @Id
    @Column(unique = true)
    private String name;

    @Column
    private String company;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @Column
    private String place;

    @Column
    private Integer courtCounter;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private boolean isDeleted;

    @ManyToMany(mappedBy = "cups")
    private Set<Team> teams = new HashSet<>();

    @ManyToMany(mappedBy = "cups")
    private Set<User> organizers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "sports_name", foreignKey = @ForeignKey(name = "fk_cups_sports"))
    private Sport sport;

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void deleteTeam(Team team) {
        teams.remove(team);
    }

    public void addOrganizer(User user) {
        organizers.add(user);
    }

    public void deleteOrganizer(User user) {
        organizers.remove(user);
    }
}
