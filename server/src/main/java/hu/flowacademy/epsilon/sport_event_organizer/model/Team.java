package hu.flowacademy.epsilon.sport_event_organizer.model;

import net.minidev.json.annotate.JsonIgnore;

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

    @ManyToMany(mappedBy = "teamMembers")
    private Set<User> users = new HashSet<>();


    @ManyToMany(mappedBy = "teamLeaders")
    private Set<User> leaders = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private Set<Cup> cups = new HashSet<>();


    public Team() {
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


    public void setUser(User user) {
        users.add(user);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }


    public Set<User> getUsers() {
        return users;
    }


    public Set<User> getLeader() {
        return leaders;
    }

    public void setLeader(User leader) {
        leaders.add(leader);
    }

    public void removeLeader(User leader) {
        leaders.remove(leader);
    }
}
