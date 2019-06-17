package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CupService {
    @Autowired
    private CupRepository cupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;


    public Cup save(Cup cup) {
        User currentUser = userService.getCurrentUser().orElse(null);
//        cup.addOrganizer(currentUser);
        cupRepository.save(cup);
        currentUser.addCup(cup);
        User user = userRepository.save(currentUser);
        cup.addOrganizer(user);
        return cup;
    }

    public Cup getCupByName(String name) {
        return cupRepository.findByName(name).orElse(null);
    }

    public List<Cup> getByCurrentOrganizer() {
        User currentUser = userService.getCurrentUser().orElse(null);
        return cupRepository.findByOrganizers(currentUser);
    }

    public Set<User> putOrganizer(String googleName, String cupName) {
        User user = userService.findUserByGoogleName(googleName).orElse(null);
        Cup cup = cupRepository.findByName(cupName).orElse(null);
        user.addCup(cup);
        userRepository.save(user);
        return cup.getOrganizers();
    }

    public Set<User> deleteOrganizer(String googleName, String cupName) {
        User user = userService.findUserByGoogleName(googleName).orElse(null);
        Cup cup = cupRepository.findByName(cupName).orElse(null);
        user.deleteCup(cup);
        userRepository.save(user);
        return cup.getOrganizers();
    }

    public Set<Team> putTeam(String teamName, String cupName) {
        Team team = teamService.getTeamByName(teamName);
        Cup cup = cupRepository.findByName(cupName).orElse(null);
        team.addCup(cup);
        teamRepository.save(team);
        return cup.getTeams();
    }

    public Set<Team> deleteTeam(String teamName, String cupName) {
        Team team = teamService.getTeamByName(teamName);
        Cup cup = cupRepository.findByName(cupName).orElse(null);
        team.deleteCup(cup);
        teamRepository.save(team);
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

    public Cup getByName(String cupName) {
        return cupRepository.findByName(cupName).orElse(null);
    }
}
