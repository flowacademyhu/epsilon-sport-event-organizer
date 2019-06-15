package hu.flowacademy.epsilon.sport_event_organizer.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cups")
public class Cup {
    @Id
    @Column(unique = true)
    private String name;

    @Column
    private String company;

    @Column
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "cups_teams",
            joinColumns = @JoinColumn(name = "cups_name"),
            inverseJoinColumns = @JoinColumn(name = "teams_name"))
    private Set<Team> teams;

    @ManyToMany(mappedBy = "teams")
    private Set<User> users = new HashSet<>();

    public Cup() {
    }


    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
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
}
