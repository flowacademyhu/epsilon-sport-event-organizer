package hu.flowacademy.epsilon.sport_event_organizer.model;


import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @Column
    private String place;

    @Column
    private Integer courtCounter;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private boolean isDeleted;

    @ManyToMany(mappedBy = "cups")
    private Set<Team> teams = new HashSet<>();

    @ManyToMany(mappedBy = "cups")
    private Set<User> organizers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "sports_name", foreignKey = @ForeignKey(name = "fk_cups_sports"))
    private Sport sport;

    public Cup() {
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getCourtCounter() {
        return courtCounter;
    }

    public void setCourtCounter(Integer courtCounter) {
        this.courtCounter = courtCounter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
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

    public Set<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void deleteTeam(Team team) {
        teams.remove(team);
    }

    public Set<User> getOrganizers() {
        return organizers;
    }

    public void addOrganizer(User user) {
        organizers.add(user);
    }

    public void deleteOrganizer(User user) {
        organizers.remove(user);
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
