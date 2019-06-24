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
        Team team3 = new Team();
        Team team4 = new Team();
        Team team5 = new Team();
        Team team6 = new Team();
        Team team7 = new Team();
        Team team8 = new Team();
        team1.setName("Hipsters");
        team2.setName("The Crew");
        team3.setName("Girls");
        team4.setName("Boys");
        team5.setName("Rockets");
        team6.setName("Real Szeged");
        team7.setName("Fradi");
        team8.setName("Újpest");
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
        cup.setCourtCounter(4);
        cup.setDescription("This is the best sport event in Szeged.");
        cup.setStartDateTime(LocalDateTime.of(2019, Month.JULY, 29, 8, 0, 0));
        cup.setEndDateTime(LocalDateTime.of(2019, Month.JULY, 29, 16, 0, 0));


        cup.setSport(sport);

        user.addTeamMember(team1);
        user1.addTeamMember(team1);
        user2.addTeamMember(team1);
        user.addTeamLeader(team1);
        user.addTeamLeader(team2);

        user.addCup(cup);
        team1.addValidatedCup(cup);
        team2.addValidatedCup(cup);
        team3.addValidatedCup(cup);
        team4.addValidatedCup(cup);
        team5.addValidatedCup(cup);
        team6.addValidatedCup(cup);
        team7.addValidatedCup(cup);
        team8.addValidatedCup(cup);


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
        teamRepository.save(team3);
        teamRepository.save(team4);
        teamRepository.save(team5);
        teamRepository.save(team6);
        teamRepository.save(team7);
        teamRepository.save(team8);
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }
}