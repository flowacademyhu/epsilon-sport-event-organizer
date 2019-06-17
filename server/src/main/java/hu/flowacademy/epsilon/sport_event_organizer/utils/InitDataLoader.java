package hu.flowacademy.epsilon.sport_event_organizer.utils;

import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitDataLoader implements CommandLineRunner {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CupRepository cupRepository;


    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        User user1 = new User();
        User user2 = new User();
        user.setGoogleName("feri");
        user1.setGoogleName("kati");
        user2.setGoogleName("béla");
        user.setProviderId("1");
        user1.setProviderId("2");
        user2.setProviderId("3");

        user.setProvider(AuthProvider.google);
        user1.setProvider(AuthProvider.google);
        user2.setProvider(AuthProvider.google);

        Team team1 = new Team();
        Team team2 = new Team();
        team1.setName("fiuk");
        team2.setName("lanyok");

        Cup cup = new Cup();
        cup.setName("Megye2");

        Set<Team> teamSet = new HashSet();
        Set<Cup> cupSet = new HashSet();


        teamSet.add(team1);
        cupSet.add(cup);

        user.setTeamMembers(teamSet);
        user1.setTeamMembers(teamSet);
        user2.setTeamMembers(teamSet);
        user.setTeamLeaders(teamSet);

        user.setCups(cupSet);
        cup.setTeams(teamSet);


        teamRepository.save(team1);
        teamRepository.save(team2);
        cupRepository.save(cup);
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);

    }
}
