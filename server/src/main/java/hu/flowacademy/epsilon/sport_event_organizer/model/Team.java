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


    public Team(String name, String company, String imageUrl) {
        this.name = name;
        this.company = company;
        this.imageUrl = imageUrl;
    }

    public Team() {
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


    public void setUsers(User user) {
        users.add(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<User> getLeaders() {
        return leaders;
    }

    public void setLeaders(User leader) {
        leaders.add(leader);
    }
}
