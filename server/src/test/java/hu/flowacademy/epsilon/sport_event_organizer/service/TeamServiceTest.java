package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.email.MailService;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class TeamServiceTest {

    @TestConfiguration
    static class TeamServiceTestConfig {

        @Bean
        public TeamService teamService() {
            return new TeamService();
        }

        @Bean
        public UserService userService() {
            return new UserService();
        }

        @Bean
        public MailService mailService() { return new MailService(); }
    }

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() {
        Team team1 = new Team();
        Team team2 = new Team();
        team1.setName("Foo");
        team2.setName("Boo");
        User user = new User();
        List<Team> teamList = new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);
        Mockito.when(teamRepository.findByName(team1.getName())).thenReturn(java.util.Optional.of(team1));
        Mockito.when(teamRepository.save(any(Team.class))).thenReturn(team1);
        Mockito.when(teamRepository.findByUsers(user)).thenReturn(teamList);
    }

    @Test
    public void whenNameSearched_thenReturnTeam() {
        String searchExpression = "Foo";
        Team teamFound = teamService.getTeamByName(searchExpression);
        assertTrue(teamFound.getName().equalsIgnoreCase(searchExpression));
    }

    //TODO continue on with test after TeamService modified
    /*
    @Test
    public void whenTeamToBeCreated_thenReturnTeamSaved() {
        Team team = new Team();
        User user = new User();
        user.addTeamLeader(team);
        team.addLeader(user);
        Team teamSaved = teamService.save(team);
        assertEquals(team, teamSaved);
    }
     */

    //TODO same here...
    /*
    @Test
    public void whenTeamToBeUpdated_thenReturnTeam() {
        Team team = new Team();
        team.setName("Foo");
        teamService.save(team);
        Team updateTeam = teamService.update(team);
        assertNotEquals(team, updateTeam);
    }
     */


}
