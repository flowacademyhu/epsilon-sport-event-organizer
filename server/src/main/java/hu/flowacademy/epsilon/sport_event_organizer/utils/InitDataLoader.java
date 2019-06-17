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
        user.setGoogleName("Flow Feri");
        user1.setGoogleName("Flow Eszter");
        user2.setGoogleName("Flow Béla");
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

        Set<Team> teamSet1 = new HashSet();
        Set<Team> teamSet2 = new HashSet();
        Set<Cup> cupSet = new HashSet();


        teamSet1.add(team1);
        teamSet2.add(team1);
        teamSet2.add(team2);
        cupSet.add(cup);

        user.setTeamMember(teamSet1);
        user1.setTeamMember(teamSet1);
        user2.setTeamMember(teamSet1);
        user.setTeamLeader(teamSet2);

        user.addCup(cup);
        team1.addCup(cup);

        cupRepository.save(cup);
        teamRepository.save(team1);
        teamRepository.save(team2);
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }
}
