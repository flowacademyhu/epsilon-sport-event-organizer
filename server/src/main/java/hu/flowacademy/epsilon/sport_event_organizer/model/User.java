package hu.flowacademy.epsilon.sport_event_organizer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
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


    public Set<Team> getTeamMembers() {
        return teamMembers;
    }


    public Set<Team> getTeamLeaders() {
        return teamLeaders;
    }


    public Set<Cup> getCups() {
        return cups;
    }


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


    public void setTeamMember(Set<Team> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void setTeamLeader(Set<Team> teamLeaders) {
        this.teamLeaders = teamLeaders;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGoogleName() {
        return googleName;
    }

    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User user = (User)obj;
        return Objects.equals(id, user.id) &&
                Objects.equals(googleName, user.googleName) &&
                Objects.equals(email, user.email);
    }
}
