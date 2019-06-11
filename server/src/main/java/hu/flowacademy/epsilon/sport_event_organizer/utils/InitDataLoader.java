package hu.flowacademy.epsilon.sport_event_organizer.utils;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataLoader implements CommandLineRunner {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {

        Team team1 = new Team("Csapat 1", "Flow", "www.kiscica.hu");

        teamRepository.save(team1);

    }
}
