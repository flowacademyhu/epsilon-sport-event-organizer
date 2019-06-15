package hu.flowacademy.epsilon.sport_event_organizer.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    @ManyToMany(mappedBy = "teams")
    private Set<User> users = new HashSet<>();


    @ManyToMany(mappedBy = "teamLeaders")
    private Set<User> leaders = new HashSet<>();

    @ManyToMany(mappedBy = "teams")
    private Set<User> cups = new HashSet<>();


    public Team(String name, String company, String imageUrl) {
        this.name = name;
        this.company = company;
        this.imageUrl = imageUrl;
    }

    public Team() {
    }


    public Set<User> getCups() {
        return cups;
    }

    public void setCups(Set<User> cups) {
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
