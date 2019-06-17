package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class CupService {
    @Autowired
    private CupRepository cupRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;


    public Cup save(Cup cup) {
        User currentUser = userService.getCurrentUser().orElse(null);
        cup.setOrganizer(currentUser);
        return cupRepository.save(cup);
    }

    public Cup getCupByName(String name) {
        return cupRepository.findByName(name).orElse(null);
    }

    public Cup getByCurrentOrganizer() {
        User currentUser = userService.getCurrentUser().orElse(null);
        return cupRepository.findByOrganizers(currentUser).orElse(null);
    }

    public Set<User> putOrganizer(String cupName, String googleName) {
        User user = userService.findUserByGoogleName(googleName).orElse(null);
        Cup cup = cupRepository.findByName(cupName).orElse(null);
        cup.setOrganizer(user);
        cupRepository.save(cup);
        return cup.getOrganizers();
    }

    public Set<User> deleteOrganizer(String cupName, String googleName) {
        User user = userService.findUserByGoogleName(googleName).orElse(null);
        Cup cup = cupRepository.findByName(cupName).orElse(null);
        cup.deleteOrganizer(user);
        cupRepository.save(cup);
        return cup.getOrganizers();
    }

    public Set<Team> putTeam(String cupName, String teamName) {
        Team team = teamService.getTeamByName(teamName);
        Cup cup = cupRepository.findByName(cupName).orElse(null);
        cup.setTeam(team);
        cupRepository.save(cup);
        return cup.getTeams();
    }

    public Set<Team> deleteTeam(String cupName, String teamName) {
        Team team = teamService.getTeamByName(teamName);
        Cup cup = cupRepository.findByName(cupName).orElse(null);
        cup.deleteTeam(team);
        cupRepository.save(cup);
        return cup.getTeams();
    }


    public Cup update(Cup cup) {
        if (cupRepository.findByName(cup.getName()).isPresent()) {
            Cup previousCup = cupRepository.findByName(cup.getName()).orElse(null);
            previousCup.setName(cup.getName());
            previousCup.setCompany(cup.getCompany());
            previousCup.setImageUrl(cup.getImageUrl());

            return cupRepository.save(previousCup);
        }
        throw new NullPointerException();
        //TODO create custom exception for non existent team
    }
}
