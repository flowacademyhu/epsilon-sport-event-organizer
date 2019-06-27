package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.email.MailService;
import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import hu.flowacademy.epsilon.sport_event_organizer.security.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class CupServiceTest {

    @TestConfiguration
    static class CupServiceTestConfig {

        @Bean
        public CupService cupService() { return new CupService(); }

        @Bean
        public TeamService teamService() { return new TeamService(); }

        @Bean
        public UserService userService() { return new UserService(); }

        @Bean
        public MailService mailService() { return new MailService(); }
    }

    @Autowired
    private CupService cupService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @MockBean
    private CupRepository cupRepository;

    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() {
        Cup cup1 = new Cup();
        String place = "place";
        cup1.setName("Foo");
        cup1.setPlace(place);
        Cup cup2 = new Cup();
        cup2.setName("Boo");
        cup2.setPlace(place);
        List<Cup> cupList = new ArrayList<>();
        cupList.add(cup1);
        cupList.add(cup2);
        User user = new User();
        user.setGoogleName("name");
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        user.setProvider(AuthProvider.google);
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(userPrincipal, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        Mockito.when(cupRepository.findByName(cup1.getName())).thenReturn(java.util.Optional.of(cup1));
        Mockito.when(cupRepository.save(any(Cup.class))).thenReturn(cup1);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findById(uuid)).thenReturn(java.util.Optional.ofNullable(user));
        Mockito.when(cupRepository.findAll()).thenReturn(cupList);
        Mockito.when(cupRepository.findByPlace(place)).thenReturn(cupList);
    }

    @Test
    public void whenNameSearched_thenReturnCup() {
        String searchName = "Foo";
        Cup cupFound = cupService.getByName(searchName);
        assertTrue(cupFound.getName().equals(searchName));
    }

    @Test
    public void whenCupToBeSaved_thenReturnCup() {
        Cup cupToBeSaved = new Cup();
        User user = new User();
        user.addCupToOrganizer(cupToBeSaved);
        cupToBeSaved.addOrganizer(user);
        cupToBeSaved.setEventDate(LocalDate.of(2019, 6, 29));
        cupToBeSaved.setRegistrationEndDate(LocalDate.of(2019, 6, 20));
        Cup cupSaved = cupService.save(cupToBeSaved);
        assertEquals(cupToBeSaved, cupSaved);
    }

    @Test
    public void whenCupToBeUpdated_thenReturnCup() {
        Cup cupToBeUpdated = new Cup();
        cupToBeUpdated.setName("Foo");
        User user = new User();
        user.addCupToOrganizer(cupToBeUpdated);
        cupToBeUpdated.addOrganizer(user);
        cupToBeUpdated.setEventDate(LocalDate.of(2019, 6, 29));
        cupToBeUpdated.setRegistrationEndDate(LocalDate.of(2019, 6, 20));
        cupService.save(cupToBeUpdated);
        Cup cupUpdated = cupService.update(cupToBeUpdated);
        assertNotEquals(cupToBeUpdated, cupUpdated);
    }

    @Test
    public void whenGetAllCups_thenReturnAllCups() {
        Cup cup1 = new Cup();
        cup1.setName("Foo");
        cup1.setPlace("place");
        Cup cup2 = new Cup();
        cup2.setName("Boo");
        cup2.setPlace("place");
        List<Cup> cupList = new ArrayList<>();
        cupList.add(cup1);
        cupList.add(cup2);
        cupRepository.save(cup1);
        cupRepository.save(cup2);
        assertEquals(cupList, cupService.getAllCups());
    }

    @Test
    public void whenPlaceSearched_thenReturnFilteredCups() {
        String place = "place";
        Cup cup1 = new Cup();
        cup1.setName("Foo");
        cup1.setPlace(place);
        Cup cup2 = new Cup();
        cup2.setName("Boo");
        cup2.setPlace(place);
        List<Cup> cupList = new ArrayList<>();
        cupList.add(cup1);
        cupList.add(cup2);
        cupRepository.save(cup1);
        cupRepository.save(cup2);
        assertEquals(cupList, cupService.getCupsByPlace(place));
    }
}
