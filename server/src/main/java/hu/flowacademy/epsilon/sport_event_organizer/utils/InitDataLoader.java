package hu.flowacademy.epsilon.sport_event_organizer.utils;

import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class InitDataLoader implements CommandLineRunner {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

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
        Set set = new HashSet();


        Team team1 = new Team();
        Team team2 = new Team();
        team1.setName("hörcsögök");
        team2.setName("piások");
        set.add(team1);
        user.setTeams(set);
        user1.setTeams(set);
        user2.setTeams(set);
        user.setTeamscsk(set);
        teamRepository.save(team1);
        teamRepository.save(team2);
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);


    }
}
