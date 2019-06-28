package hu.flowacademy.epsilon.sport_event_organizer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cups")
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(exclude = {"isDeleted", "teams", "organizers", "approved", "sport"})
public class Cup {

    @Id
    @Column(unique = true)
    @ToString.Include
    private String name;

    @Column
    @ToString.Include
    private String company;

    @Column
    private String imageUrl;

    @Column
    @ToString.Include
    private String place;

    @Column
    private Integer courtCounter;

    @Column(columnDefinition = "TEXT")
    @ToString.Include
    private String description;

    @Column
    private boolean isDeleted;

    @Column
    private LocalDate eventDate;

    @Column
    private LocalDate registrationEndDate;

    @Column
    private String sportType;

    @ManyToMany(mappedBy = "cups")
    private Set<Team> teams = new HashSet<>();

    @ManyToMany(mappedBy = "cups")
    private Set<User> organizers = new HashSet<>();

    @ManyToMany(mappedBy = "validatedCups")
    private Set<Team> approved = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sports_name", foreignKey = @ForeignKey(name = "fk_cups_sports"))
    private Sport sport;

    @OneToMany(mappedBy = "cup")
    private Set<Match> matches = new HashSet<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void approveTeam(Team team) {
        teams.remove(team);
        approved.add(team);
    }

    public void refuseTeam(Team team) {
        teams.remove(team);
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
