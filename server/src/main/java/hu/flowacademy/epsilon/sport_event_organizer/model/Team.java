package hu.flowacademy.epsilon.sport_event_organizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
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


    public Team() {
    }

    public void addCup(Cup cup) {
        if (cups == null) {
            this.cups = new HashSet<>();
        }
        cups.add(cup);
    }

    public void deleteCup(Cup cup) {
        if (cups == null) {
            this.cups = new HashSet<>();
        }
        cups.remove(cup);
    }

    public Set<Cup> getCups() {
        return cups;
    }

    public void setCups(Set<Cup> cups) {
        this.cups = cups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<User> getMembers() {
        return users;
    }

    public void addMember(User user) {
        users.add(user);
    }

    public void deleteMember(User user) {
        users.remove(user);
    }

    public Set<User> getLeaders() {
        return leaders;
    }

    public void addLeader(User leader) {
        leaders.add(leader);
    }

    public void deleteLeader(User leader) {
        leaders.remove(leader);
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}