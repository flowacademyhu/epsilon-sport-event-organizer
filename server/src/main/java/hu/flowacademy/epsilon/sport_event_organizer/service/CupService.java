package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.exception.CupNotFoundException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class CupService {
    @Autowired
    private CupRepository cupRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    public Cup save(Cup cup) {
        User currentUser = userService.getCurrentUser();
        cup.setDeleted(false);
        cupRepository.save(cup);
        currentUser.addCup(cup);
        userService.save(currentUser);
        cup.addOrganizer(currentUser);
        return cup;
    }

    public Cup update(Cup cup) {
        Cup previousCup = cupRepository.findByName(cup.getName()).orElseThrow(() -> new CupNotFoundException(cup.getName()));
        previousCup.setName(cup.getName());
        previousCup.setCompany(cup.getCompany());
        previousCup.setImageUrl(cup.getImageUrl());
        return cupRepository.save(previousCup);
    }

    public Cup getByName(String name) {
        Cup cup = cupRepository.findByName(name).orElseThrow(() -> new CupNotFoundException(name));
        if (!cup.isDeleted()) {
            return cup;
        }
        throw new CupNotFoundException(name);
    }

    public List<Cup> getAllCups() {
        return cupRepository.findAll();
    }

    public List<Cup> getAllNonDeletedCups() {
        List<Cup> cupList = cupRepository.findAll();
        for (Cup cup: cupList) {
            if (cup.isDeleted()) {
                cupList.remove(cup);
            }
        }
        log.info(cupList.toString());
        return cupList;
    }

    // returning only non-deleted cups!
    public List<Cup> getCupsByPlace(String place) {
        List<Cup> cupList = cupRepository.findByPlace(place);
        return cupList.stream()
                .filter(cup -> !cup.isDeleted())
                .collect(Collectors.toList());
    }

    // returning only non-deleted cups!
    public List<Cup> getCupsByCompany(String company) {
        List<Cup> cupList = cupRepository.findByCompany(company);
        return cupList.stream()
                .filter(cup -> !cup.isDeleted())
                .collect(Collectors.toList());
    }

    public List<Cup> getByCurrentOrganizer() {
        User currentUser = userService.getCurrentUser();
        List<Cup> cups = cupRepository.findByOrganizers(currentUser);
        cups.removeIf(Cup::isDeleted);
        return cups;
    }

    public Set<User> putOrganizer(String googleName, String cupName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        userToAdd.addCup(cup);
        userService.save(userToAdd);
        Set<User> users = cup.getOrganizers();
        users.removeIf(User::isDeleted);
        return users;
    }

    public Set<User> deleteOrganizer(String googleName, String cupName) {
        User userToRemove = userService.findUserByGoogleName(googleName);
        Cup cups = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        userToRemove.deleteCup(cups);
        userService.save(userToRemove);
        Set<User> users = cups.getOrganizers();
        users.removeIf(User::isDeleted);
        if (users.size() == 1) {
            throw new RuntimeException();
            //TODO write a normal exception
        }
        return users;
    }

    public Set<Team> putTeam(String teamName, String cupName) {
        Team teamToAdd = teamService.getTeamByName(teamName);
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        teamToAdd.addCup(cup);
        teamService.save(teamToAdd);
        Set<Team> teams = cup.getTeams();
        teams.removeIf(Team::isDeleted);
        return teams;
    }

    public Set<Team> deleteTeam(String teamName, String cupName) {
        Team teamToRemove = teamService.getTeamByName(teamName);
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        teamToRemove.deleteCup(cup);
        teamService.save(teamToRemove);
        Set<Team> teams = cup.getTeams();
        teams.removeIf(Team::isDeleted);
        return teams;
    }

    public void deleteCupByName(String cupName) {
        cupRepository.updateDelete(cupName, true);
    }
}
