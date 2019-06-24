package hu.flowacademy.epsilon.sport_event_organizer.utils;

import hu.flowacademy.epsilon.sport_event_organizer.model.*;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.SportRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Component
@Transactional
public class InitDataLoader implements CommandLineRunner {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CupRepository cupRepository;

    @Autowired
    private SportRepository sportRepository;


    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        User user1 = new User();
        User user2 = new User();
        user.setGoogleName("Flow Feri");
        user1.setGoogleName("Flow Eszter");
        user2.setGoogleName("Flow Bela");
        user.setProviderId("1");
        user1.setProviderId("2");
        user2.setProviderId("3");
        user.setProvider(AuthProvider.google);
        user1.setProvider(AuthProvider.google);
        user2.setProvider(AuthProvider.google);
        user.setCompanyName("Flow Academy");
        user1.setCompanyName("Flow Academy");
        user2.setCompanyName("Flow Academy");

        Team team1 = new Team();
        Team team2 = new Team();
        team1.setName("Hipsters");
        team2.setName("The Crew");
        team1.setCompany("Flow Academy");
        team2.setCompany("Flow Academy");

        Sport sport = new Sport();
        Sport sport1 = new Sport();
        Sport sport2 = new Sport();
        sport.setName("football");
        sport1.setName("basketball");
        sport2.setName("handball");
        sport.setBreakCounter(1);
        sport.setBreakDurationInMinutes(15);

        Cup cup = new Cup();
        cup.setName("Flow Cup");
        cup.setCompany("Flow Academy");
        cup.setPlace("Szeged, Hattyas u. 10, 6725");
        cup.setCourtCounter(3);
        cup.setDescription("This is the best sport event in Szeged.");
        cup.setEventDate(LocalDate.of(2019, 6, 30));
        cup.setRegistrationEndDate(LocalDate.of(2019, 6, 27));

        cup.setSport(sport);

        user.addTeamMember(team1);
        user1.addTeamMember(team1);
        user2.addTeamMember(team1);
        user.addTeamLeader(team1);
        user.addTeamLeader(team2);

        user.addCup(cup);
        team1.addCup(cup);

        Cup cup2 = new Cup();
        cup2.setName("test");
        cup2.setCompany("Flow Academy");
        cup2.setPlace("Hattyas");
        cup2.setCourtCounter(1);
        cup2.setDescription("n.a");
        cup2.setDeleted(false);

        user.addCup(cup2);
        team2.addValidatedCup(cup2);

        sportRepository.save(sport);
        sportRepository.save(sport1);
        sportRepository.save(sport2);
        cupRepository.save(cup);
        cupRepository.save(cup2);
        teamRepository.save(team1);
        teamRepository.save(team2);
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }
}