package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.exception.CupNotFoundException;
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
        cup.setDeleted(false);
        cupRepository.save(cup);
        currentUser.addCup(cup);
        User user = userRepository.save(currentUser);
        cup.addOrganizer(user);
        return cup;
    }

    public Cup getCupByName(String cupName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        if (!cup.isDeleted()) {
            return cup;
        }
        throw new CupNotFoundException(cupName);
    }

    public Cup update(Cup cup) {
        Cup previousCup = cupRepository.findByName(cup.getName()).orElse(null);
        previousCup.setName(cup.getName());
        previousCup.setCompany(cup.getCompany());
        previousCup.setImageUrl(cup.getImageUrl());
        return cupRepository.save(previousCup);

    }

    public Cup getByName(String cupName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        if (!cup.isDeleted()) {
            return cup;
        }
        throw new CupNotFoundException(cupName);
    }

    public List<Cup> getByCurrentOrganizer() {
        User currentUser = userService.getCurrentUser().orElse(null);

        List<Cup> cups = cupRepository.findByOrganizers(currentUser);
        cups.removeIf(cup -> cup.isDeleted());
        return cups;
    }

    public Set<User> putOrganizer(String googleName, String cupName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));

        userToAdd.addCup(cup);
        userRepository.save(userToAdd);
        Set<User> users = cup.getOrganizers();
        users.removeIf(user -> user.isDeleted());
        return users;
    }

    public Set<User> deleteOrganizer(String googleName, String cupName) {
        User userToRemove = userService.findUserByGoogleName(googleName);
        Cup cups = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        userToRemove.deleteCup(cups);
        userRepository.save(userToRemove);
        Set<User> users = cups.getOrganizers();
        users.removeIf(user -> user.isDeleted());
        return users;
    }

    public Set<Team> putTeam(String teamName, String cupName) {
        Team teamToAdd = teamService.getTeamByName(teamName);
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        teamToAdd.addCup(cup);
        teamRepository.save(teamToAdd);
        Set<Team> teams = cup.getTeams();
        teams.removeIf(team -> team.isDeleted());
        return teams;
    }

    public Set<Team> deleteTeam(String teamName, String cupName) {
        Team teamToRemove = teamService.getTeamByName(teamName);
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        teamToRemove.deleteCup(cup);
        teamRepository.save(teamToRemove);
        Set<Team> teams = cup.getTeams();
        teams.removeIf(team -> team.isDeleted());
        return teams;
    }

    public void deleteCup(String cupName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        cup.setDeleted(true);
        cupRepository.save(cup);
    }
}
