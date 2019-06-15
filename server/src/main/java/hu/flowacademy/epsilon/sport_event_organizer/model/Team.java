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
    private Set<User> users;

    @ManyToMany(mappedBy = "teamLeaders")
    private Set<User> leaders;


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

}
