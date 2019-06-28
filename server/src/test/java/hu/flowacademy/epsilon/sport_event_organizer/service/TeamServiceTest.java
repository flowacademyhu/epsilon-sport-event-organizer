package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.email.MailService;
import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import hu.flowacademy.epsilon.sport_event_organizer.security.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String company = "A";
        team1.setCompany(company);
        team2.setCompany(company);
        User user = new User();
        user.setGoogleName("name");
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        user.setProvider(AuthProvider.google);
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(userPrincipal, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        List<Team> teamList = new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);
        Mockito.when(teamRepository.findByName(team1.getName())).thenReturn(java.util.Optional.of(team1));
        Mockito.when(teamRepository.save(any(Team.class))).thenReturn(team1);
        Mockito.when(teamRepository.findByUsers(user)).thenReturn(teamList);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findById(uuid)).thenReturn(java.util.Optional.ofNullable(user));
        Mockito.when(teamRepository.findAll()).thenReturn(teamList);
        Mockito.when(teamRepository.findByCompany(company)).thenReturn(teamList);
    }

    @Test
    public void whenNameSearched_thenReturnTeam() {
        String searchExpression = "Foo";
        Team teamFound = teamService.getTeamByName(searchExpression);
        assertTrue(teamFound.getName().equalsIgnoreCase(searchExpression));
    }

    @Test
    public void whenCurrentUser_thenReturnHisTeams() {
        User user = new User();
        Team team1 = new Team();
        team1.setName("Foo");
        team1.setCompany("A");
        Team team2 = new Team();
        team2.setName("Boo");
        team2.setCompany("A");
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        user.addTeamMember(team1);
        user.addTeamLeader(team2);
        userRepository.save(user);
        teamRepository.save(team1);
        teamRepository.save(team2);
        assertEquals(teamService.getByCurrentUser(), teams);
    }

    @Test
    public void whenGetAllTeams_thenReturnAllNonDeletedTeams() {
        Team team3 = new Team();
        team3.setName("Foo");
        team3.setCompany("A");
        Team team4 = new Team();
        team4.setName("Boo");
        team4.setCompany("A");
        List<Team> teamList = new ArrayList<>();
        teamList.add(team3);
        teamList.add(team4);
        teamRepository.save(team3);
        teamRepository.save(team4);
        assertEquals(teamList, teamService.getAllNonDeletedTeams());
    }

    @Test
    public void whenSearchedByCompany_thenReturnAllByCompany() {
        String company = "A";
        Team team5 = new Team();
        team5.setName("Foo");
        team5.setCompany(company);
        Team team6 = new Team();
        team6.setName("Boo");
        team6.setCompany(company);
        List<Team> teamList = new ArrayList<>();
        teamList.add(team5);
        teamList.add(team6);
        teamRepository.save(team5);
        teamRepository.save(team6);
        assertEquals(teamList, teamService.getAllTeamsByCompany(company));

    }
}
