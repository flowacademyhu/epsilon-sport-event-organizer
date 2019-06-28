package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CupRespositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CupRepository cupRepository;

    @Test
    public void whenCupNameSearched_thenReturnCup() {
        Cup cupToBeFound = new Cup();
        cupToBeFound.setName("test");
        testEntityManager.persist(cupToBeFound);
        testEntityManager.flush();
        Optional<Cup> cupFound = cupRepository.findByName(cupToBeFound.getName());
        assertThat(cupToBeFound.getName()).isEqualTo(cupFound.map(Cup::getName).orElse(null));
    }

    @Test
    public void whenCupDelete_thenSetIsDeletedTrue() {
        Cup cupToDelete = new Cup();
        cupToDelete.setName("test");
        cupRepository.updateDelete("test", false);
        testEntityManager.persist(cupToDelete);
        testEntityManager.flush();
        assertThat(cupToDelete.isDeleted()).isFalse();
    }

    @Test
    public void whenPlaceSearched_thenReturnCupList() {
        String place = "test";
        Cup cup1 = new Cup();
        Cup cup2 = new Cup();
        cup1.setName("A");
        cup2.setName("B");
        cup1.setPlace(place);
        cup2.setPlace(place);
        List<Cup> cupList = new ArrayList<>();
        cupList.add(cup1);
        cupList.add(cup2);
        testEntityManager.persist(cup1);
        testEntityManager.persist(cup2);
        testEntityManager.flush();
        assertEquals(cupList, cupRepository.findByPlace("test"));
    }

    @Test
    public void whenCompanySearched_thenReturnCupList() {
        String company = "test";
        Cup cup1 = new Cup();
        Cup cup2 = new Cup();
        cup1.setName("C");
        cup2.setName("D");
        cup1.setCompany(company);
        cup2.setCompany(company);
        List<Cup> cupList = new ArrayList<>();
        cupList.add(cup1);
        cupList.add(cup2);
        testEntityManager.persist(cup1);
        testEntityManager.persist(cup2);
        testEntityManager.flush();
        assertEquals(cupList, cupRepository.findByCompany("test"));
    }

    @Test
    public void whenSearchedByTeam_thenReturnCuplist() {
        Team team = new Team();
        team.setName("team");
        Cup cup1 = new Cup();
        cup1.setName("E");
        cup1.addTeam(team);
        Cup cup2 = new Cup();
        cup2.setName("F");
        cup2.addTeam(team);
        Set<Cup> cupSet = new HashSet<>();
        cupSet.add(cup1);
        cupSet.add(cup2);
        team.addCup(cup1);
        team.addCup(cup2);
        testEntityManager.persist(team);
        testEntityManager.persist(cup1);
        testEntityManager.persist(cup2);
        testEntityManager.flush();
        assertEquals(cupSet, cupRepository.findByTeams(team));
    }

    @Test
    public void whenSearchedByTeam_thenReturnApprovedCuplist() {
        Team team = new Team();
        team.setName("team");
        Cup cup1 = new Cup();
        cup1.setName("E");
        cup1.approveTeam(team);
        Cup cup2 = new Cup();
        cup2.setName("F");
        cup2.approveTeam(team);
        Set<Cup> cupSet = new HashSet<>();
        cupSet.add(cup1);
        cupSet.add(cup2);
        team.addValidatedCup(cup1);
        team.addValidatedCup(cup2);
        testEntityManager.persist(team);
        testEntityManager.persist(cup1);
        testEntityManager.persist(cup2);
        testEntityManager.flush();
        assertEquals(cupSet, cupRepository.findByApproved(team));
    }
}
