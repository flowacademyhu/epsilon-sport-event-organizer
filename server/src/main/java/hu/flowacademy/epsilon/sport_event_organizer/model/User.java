package hu.flowacademy.epsilon.sport_event_organizer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
@EqualsAndHashCode(exclude = {"teamMembers", "teamLeaders", "cups", "emailVerified", "providerId", "provider", "accessToken", "isDeleted"})
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(unique = true)
    private String googleName;

    @Email
    @Column
    private String email;

    @Column
    private String imageUrl;

    @Column
    private Boolean emailVerified = false;

    @Column
    private String providerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column
    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String accessToken;

    @Column
    private boolean isDeleted;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "teams_members",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_name"))
    private Set<Team> teamMembers;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "teams_leaders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_name"))
    private Set<Team> teamLeaders;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "cups_organizers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "cup_name"))
    private Set<Cup> cups;

    public void addTeamMember(Team team) {
        if (teamMembers == null) {
            this.teamMembers = new HashSet<>();
        }
        teamMembers.add(team);
    }

    public void deleteTeamMember(Team team) {
        if (teamMembers == null) {
            this.teamMembers = new HashSet<>();
        }
        teamMembers.remove(team);
    }

    public void addTeamLeader(Team team) {
        if (teamLeaders == null) {
            this.teamLeaders = new HashSet<>();
        }
        teamLeaders.add(team);
    }

    public void deleteTeamLeader(Team team) {
        if (teamLeaders == null) {
            this.teamLeaders = new HashSet<>();
        }
        teamLeaders.remove(team);
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
}
