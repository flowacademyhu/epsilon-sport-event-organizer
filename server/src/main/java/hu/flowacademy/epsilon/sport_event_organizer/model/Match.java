package hu.flowacademy.epsilon.sport_event_organizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;


@Entity
@Table(name = "matches")
@Data
public class Match {

    @Id
    @SequenceGenerator(name = "matchItemSeqGenerator", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "matchItemSeqGenerator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_match_cup"))
    private Cup cup;


    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_match_team"))
    private Team teamA;


    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_match_team"))
    private Team teamB;

    @Column(name = "teamA_score")
    private Integer teamAScore;

    @Column(name = "teamB_score")
    private Integer teamBScore;

    @Column
    private String courtName;

    @Column
    private String groupName;


}
