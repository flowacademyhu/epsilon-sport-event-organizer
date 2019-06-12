package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team update(Team team) {
        if (teamRepository.findByName(team.getName()).isPresent()) {
            return teamRepository.save(team);
        }
        throw new NullPointerException();
        //TODO create custom exception for non existant team
    }

    public Optional<Team> getTeamByName(String name) {
        return teamRepository.findByName(name);
    }

    public Optional<Team> getTeamByCompany(String company) {
        return teamRepository.findByCompany(company);
    }

    //TODO create query for getting team(s) by user
}
