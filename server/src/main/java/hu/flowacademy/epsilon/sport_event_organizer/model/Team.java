package hu.flowacademy.epsilon.sport_event_organizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data
@EqualsAndHashCode(exclude = {"users", "leaders", "cups", "isDeleted"})
public class Team {

    @Id
    @Column(unique = true)
    private String name;

    @Column
    private String company;

    @Column
    private String imageUrl;

    @Column
    private boolean isDeleted;

    private int winnerCounter;

    private String groupName;

    private boolean isQualified;


    @ManyToMany(mappedBy = "teamMembers")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "teamLeaders")
    private Set<User> leaders = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "cups_teams",
            joinColumns = @JoinColumn(name = "teams_name"),
            inverseJoinColumns = @JoinColumn(name = "cups_name"))
    private Set<Cup> cups;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "cups_approved",
            joinColumns = @JoinColumn(name = "teams_name"),
            inverseJoinColumns = @JoinColumn(name = "cups_name"))
    private Set<Cup> validated;


    public void addCup(Cup cup) {
        if (cups == null) {
            this.cups = new HashSet<>();
        }
        cups.add(cup);
    }

    public void addValidatedCup(Cup cup) {
        if (validated == null) {
            this.validated = new HashSet<>();
        }
        validated.add(cup);
    }

    public void deleteCup(Cup cup) {
        if (cups == null) {
            this.cups = new HashSet<>();
        }
        cups.remove(cup);
    }

    public void addMember(User user) {
        users.add(user);
    }

    public void deleteMember(User user) {
        users.remove(user);
    }

    public void addLeader(User leader) {
        leaders.add(leader);
    }

    public void deleteLeader(User leader) {
        leaders.remove(leader);
    }


}