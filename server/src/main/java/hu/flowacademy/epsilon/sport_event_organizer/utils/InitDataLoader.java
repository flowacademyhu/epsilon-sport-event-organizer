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

import java.util.HashSet;
import java.util.Set;

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

        Cup cup = new Cup();
        cup.setName("Flow Cup");
        cup.setCompany("Flow Academy");

        Sport sport = new Sport();
        Sport sport1 = new Sport();
        Sport sport2 = new Sport();
        sport.setName("football");
        sport1.setName("basketball");
        sport2.setName("handball");

        user.addTeamMember(team1);
        user1.addTeamMember(team1);
        user2.addTeamMember(team1);
        user.addTeamLeader(team1);
        user.addTeamLeader(team2);

        user.addCup(cup);
        team1.addCup(cup);

        sportRepository.save(sport);
        sportRepository.save(sport1);
        sportRepository.save(sport2);
        cupRepository.save(cup);
        teamRepository.save(team1);
        teamRepository.save(team2);
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }
}