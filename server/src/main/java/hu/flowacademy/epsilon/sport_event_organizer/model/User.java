package hu.flowacademy.epsilon.sport_event_organizer.model;

import org.hibernate.annotations.GenericGenerator;

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

    @Column(nullable = false)
    private String googleName;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Column
    private String providerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(name = "company_name")
    private String companyName;


    //    private Instant expiresAt;
//    @Column
//    private String accessToken;

//    @Column(columnDefinition = "TEXT")
//    @JsonIgnore
//    private String password;

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

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

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

//    public void setAccessToken(String tokenValue) {
//        this.accessToken = tokenValue;
//    }

//    public void setExpiresAt(Instant expiresAt) {
//        this.expiresAt = expiresAt;
//    }

//    public String getAccessToken() {
//        return accessToken;
//    }

//    public Instant getExpiresAt() {
//        return expiresAt;
//    }
}
