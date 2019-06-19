package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thanReturnTeam() {
        Team teamToBeFound = new Team();
        teamToBeFound.setName("Team Foo");
        testEntityManager.persist(teamToBeFound);
        testEntityManager.flush();
        Optional<Team> teamFound = teamRepository.findByName(teamToBeFound.getName());
        assertThat(teamToBeFound.getName()).isEqualTo(teamFound.map(Team::getName).orElse(null));
    }

    @Test
    public void whenUserSearched_thanReturnUsersMemberTeams() {
        User user = new User();
        user.setProvider(AuthProvider.google);
        Team team1 = new Team();
        Team team2 = new Team();
        team1.setName("test eins");
        team2.setName("test zwei");
        Set<Team> teamSet = new HashSet<>();
        teamSet.add(team1);
        teamSet.add(team2);
        user.setTeamMembers(teamSet);
        testEntityManager.persist(user);
        testEntityManager.persist(team1);
        testEntityManager.persist(team2);
        testEntityManager.flush();
        List<Team> teamListFound = teamRepository.findByUsers(user);
        Set<Team> teamConverted = new HashSet<>(teamListFound);
        assertThat(user.getTeamMembers()).isEqualTo(teamConverted);
    }

    @Test
    public void whenUserSearched_thanReturnUsersLeaderTeams() {
        User user = new User();
        user.setProvider(AuthProvider.google);
        Team team1 = new Team();
        Team team2 = new Team();
        team1.setName("test eins");
        team2.setName("test zwei");
        Set<Team> teamSet = new HashSet<>();
        teamSet.add(team1);
        teamSet.add(team2);
        user.setTeamLeaders(teamSet);
        testEntityManager.persist(user);
        testEntityManager.persist(team1);
        testEntityManager.persist(team2);
        testEntityManager.flush();
        List<Team> teamListFound = teamRepository.findByLeaders(user);
        Set<Team> teamConverted = new HashSet<>(teamListFound);
        assertThat(user.getTeamLeaders()).isEqualTo(teamConverted);
    }
    
}
