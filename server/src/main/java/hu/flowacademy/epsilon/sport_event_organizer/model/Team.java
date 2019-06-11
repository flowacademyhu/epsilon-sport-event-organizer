package hu.flowacademy.epsilon.sport_event_organizer.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Column
    private String company;

    @Column
    private String imageUrl;

    public Team(String name, String company, String imageUrl) {
        this.name = name;
        this.company = company;
        this.imageUrl = imageUrl;
    }

    public Team() {}

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
