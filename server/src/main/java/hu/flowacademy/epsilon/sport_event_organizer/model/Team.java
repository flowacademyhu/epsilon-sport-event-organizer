package hu.flowacademy.epsilon.sport_event_organizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(exclude = {"users", "leaders", "cups", "validatedCups", "isDeleted"})
public class Team {

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
    private boolean isDeleted;

    @JsonIgnore
    private int winnerCounter;

    @JsonIgnore
    private String groupName;

    @JsonIgnore
    private boolean isQualified;

    @JsonIgnore
    private int goalDifference;


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
    private Set<Cup> validatedCups;


    public void addCup(Cup cup) {
        if (cups == null) {
            this.cups = new HashSet<>();
        }
        cups.add(cup);
    }

    public void addValidatedCup(Cup cup) {
        if (validatedCups == null) {
            this.validatedCups = new HashSet<>();
        }
        validatedCups.add(cup);
        deleteCup(cup);
    }

    public void refusedCup(Cup cup) {
        cups.remove(cup);
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