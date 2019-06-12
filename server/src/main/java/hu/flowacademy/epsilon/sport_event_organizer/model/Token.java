package hu.flowacademy.epsilon.sport_event_organizer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @Column
    private String userid;

    @Column(columnDefinition="TEXT")
    private String accesToken;

    @Column
    private Instant createdat;

    @Column
    private Instant expiradat;

    @Column
    private boolean isDeleted;

    public Token() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getExpiradat() {
        return expiradat;
    }

    public void setExpiradat(Instant expiradat) {
        this.expiradat = expiradat;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
