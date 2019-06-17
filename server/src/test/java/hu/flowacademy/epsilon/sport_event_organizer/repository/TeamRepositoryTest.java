package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void whenFindByName_thanReturnTeam() {
        Team teamToBeFound = new Team();
        teamToBeFound.setName("Team Foo");
        testEntityManager.persist(teamToBeFound);
        testEntityManager.flush();
        Optional<Team> teamFound = teamRepository.findByName(teamToBeFound.getName());
        assertThat(teamToBeFound.getName()).isEqualTo(teamFound.map(Team::getName).orElse(null));
    }
    
}
