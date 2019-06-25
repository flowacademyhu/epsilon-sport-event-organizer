package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.email.MailService;
import hu.flowacademy.epsilon.sport_event_organizer.exception.CupNotFoundException;
import hu.flowacademy.epsilon.sport_event_organizer.exception.UserUnauthorizedException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MailService mailService;


    public Cup save(Cup cup) {
        User currentUser = userService.getCurrentUser();
        cup.setDeleted(false);
        LocalDate cupTomorrow = cup.getEventDate().plusDays(1);
        LocalDate regTomorrow = cup.getRegistrationEndDate().plusDays(1);
        cup.setEventDate(cupTomorrow);
        cup.setRegistrationEndDate(regTomorrow);
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

    // returning all non-deleted cups!
    public List<Cup> getAllCups() {
        List<Cup> cupList = cupRepository.findAll();
        return cupList.stream()
                .filter(cup -> !cup.isDeleted())
                .collect(Collectors.toList());
    }

    // returning only non-deleted cups - by place!
    public List<Cup> getCupsByPlace(String place) {
        List<Cup> cupList = cupRepository.findByPlace(place);
        return cupList.stream()
                .filter(cup -> !cup.isDeleted())
                .collect(Collectors.toList());
    }

    // returning only non-deleted cups - by company!
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

    public List<Cup> getCupsByParticipation() {
        User user = userService.getCurrentUser();
        String name = user.getGoogleName();
        List<Cup> cups = cupRepository.findAll();
        return cups.stream()
                .filter(cup -> cup.getApproved().contains(name))
                .filter(cup -> !cup.isDeleted())
                .collect(Collectors.toList());
    }

    public void applyTeam(String cupName, String teamName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        Team team = teamService.getTeamByName(teamName);
        User currentUser = userService.getCurrentUser();
        if (team.getLeaders().contains(currentUser)) {
            mailService.sendMailOrganizersToAppliedTeam(team, cup);
            cup.addTeam(team);
            team.addCup(cup);
            teamService.update(team);
            cupRepository.save(cup);
        } else {
            throw new UserUnauthorizedException();
        }
    }


    public Set<Team> getAppliedTeams(String cupName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        return teamRepository.findByCups(cup);
    }


    public Set<Team> getApprovedTeams(String cupName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        return teamRepository.findByValidatedCups(cup);
    }

    public void approveTeam(String cupName, String teamName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        Set<Team> teams = getAppliedTeams(cupName);
        Team teamToApprove = teamService.getTeamByName(teamName);
        User user = userService.getCurrentUser();
        if (cup.getOrganizers().contains(user) && teams.contains(teamToApprove)) {
            mailService.sendMailTeamLeaderBecauseTeamApproved(teamToApprove, cup);
            cup.approveTeam(teamToApprove);
            teamToApprove.addValidatedCup(cup);
            teamService.update(teamToApprove);
            cupRepository.save(cup);
        } else {
            throw new UserUnauthorizedException();
        }
    }

    public void refuseTeam(String cupName, String teamName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        Set<Team> teams = getAppliedTeams(cupName);
        Team teamToRefuse = teamService.getTeamByName(teamName);
        User user = userService.getCurrentUser();
        if (cup.getOrganizers().contains(user) && teams.contains(teamToRefuse)) {
            mailService.sendMailTeamLeaderBecauseTeamRefused(teamToRefuse, cup);
            cup.refuseTeam(teamToRefuse);
            teamToRefuse.refusedCup(cup);
            teamService.update(teamToRefuse);
            cupRepository.save(cup);
        } else {
            throw new UserUnauthorizedException();
        }
    }

    public Set<User> getOrganizers(String cupName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        return userService.findByCupOrganizers(cup);
    }

    public void addOrganizer(String cupName, String googleName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        User currentUser = userService.getCurrentUser();
        User userToMakeOrganizer = userService.findUserByGoogleName(googleName);
        if (getOrganizers(cupName).contains(currentUser) && !cup.isDeleted() && !userToMakeOrganizer.isDeleted() && !getOrganizers(cupName).contains(userToMakeOrganizer)) {
            userToMakeOrganizer.addCup(cup); //elnevezés rossz, de valójában a kup szervezőkhöz adjuk hozzá, nem a kupához!
            cup.addOrganizer(userToMakeOrganizer);
            userService.save(userToMakeOrganizer);
            update(cup);
        } else {
            throw new UserUnauthorizedException();
        }
    }

    public void deleteOrganizer(String cupName, String googleName) {
        Cup cup = cupRepository.findByName(cupName).orElseThrow(() -> new CupNotFoundException(cupName));
        User currentUser = userService.getCurrentUser();
        User userToDelete = userService.findUserByGoogleName(googleName);
        int organizerCount = getOrganizers(cupName).size();
        if (organizerCount > 1 && getOrganizers(cupName).contains(currentUser) && getOrganizers(cupName).contains(userToDelete)) {
            userToDelete.deleteCup(cup); //itt is szervezőre értjük...
            cup.deleteOrganizer(userToDelete);
            userService.save(userToDelete);
            update(cup);
        } else {
            throw new UserUnauthorizedException();
        }
    }

    public void deleteCupByName(String cupName) {
        cupRepository.updateDelete(cupName, true);
    }
}
