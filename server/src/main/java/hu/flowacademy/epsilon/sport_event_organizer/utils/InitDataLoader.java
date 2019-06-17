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
        user.setGoogleName("Feri");
        user1.setGoogleName("Matyi");
        user2.setGoogleName("Béla");
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
        team1.setName("fiuk");
        team2.setName("lanyok");
        team1.setCompany("Flow Academy");
        team2.setCompany("Flow Academy");


        Cup cup = new Cup();
        cup.setName("Megye2");

        Set<Team> teamSet1 = new HashSet();
        Set<Team> teamSet2 = new HashSet();
        Set<Cup> cupSet = new HashSet();


        teamSet1.add(team1);
        teamSet2.add(team1);
        teamSet2.add(team2);
        cupSet.add(cup);

        user.setTeamMembers(teamSet1);
        user1.setTeamMembers(teamSet1);
        user2.setTeamMembers(teamSet1);

        user.setTeamLeaders(teamSet2);

        user.setCups(cupSet);
        team1.setCups(cupSet);

        cupRepository.save(cup);
        teamRepository.save(team1);
        teamRepository.save(team2);
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);

    }
}
