package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.email.MailService;
import hu.flowacademy.epsilon.sport_event_organizer.exception.TeamNotFoundException;
import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.validation.TeamValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private TeamValidation teamValidation;

    public Team getTeamByName(String teamName) {
        return teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException());
    }

    public List<Team> getAllNonDeletedTeams() {
        List<Team> teamList = teamRepository.findAll();
        return teamList.stream()
                .filter(team -> !team.isDeleted())
                .collect(Collectors.toList());
    }

    //returning all non-deleted Teams by Company
    public List<Team> getAllTeamsByCompany(String company) {
        List<Team> teamList = teamRepository.findByCompany(company);
        return teamList.stream()
                .filter(team -> !team.isDeleted())
                .collect(Collectors.toList());
    }

    public Team save(Team team) {
        teamValidation.validateTeamNameBeforeSave(team);
        User currentUser = userService.getCurrentUser();
        team.setDeleted(false);
        teamRepository.save(team);
        currentUser.addTeamLeader(team);
        User user = userService.save(currentUser);
        team.addLeader(user);
        teamValidation.validateTeamLeaderBeforeSave(team);
        return team;
    }

    public Team update(Team team) {
        Team previousTeam = teamRepository.findByName(team.getName()).orElseThrow(() -> new TeamNotFoundException());
        previousTeam.setName(team.getName());
        previousTeam.setCompany(team.getCompany());
        previousTeam.setImageUrl(team.getImageUrl());
        return teamRepository.save(previousTeam);
    }

    public List<Team> getByCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return teamRepository.findByUsers(currentUser);
    }

    public Team putMember(String teamName, String googleName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException());
        teamValidation.validateUserBeforePutMember(userToAdd, team);
        userToAdd.addTeamMember(team);
        userService.save(userToAdd);
        team.addMember(userToAdd);
        mailService.sendMailAddUserToTeamMember(userToAdd, team);
        return team;
    }


    public Team deleteMember(String teamName, String googleName) {
        User userToRemove = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException());
        userToRemove.deleteTeamMember(team);
        userService.save(userToRemove);
        team.deleteMember(userToRemove);
        mailService.sendMailDeleteUserFromTeam(userToRemove, team);
        return team;
    }

    public List<Team> getByCurrentLeader() {
        User currentUser = userService.getCurrentUser();
        List<Team> teamList = teamRepository.findByLeaders(currentUser);
        return teamList.stream()
                .filter(team -> !team.isDeleted())
                .collect(Collectors.toList());
    }

    public Team putLeader(String teamName, String googleName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException());
        if (team.getUsers().contains(userToAdd)) {
            team.getUsers().remove(userToAdd);
            userToAdd.deleteTeamMember(team);
        }
        userToAdd.addTeamLeader(team);
        userService.save(userToAdd);
        team.addLeader(userToAdd);
        mailService.sendMailAddUserToTeamLeader(userToAdd, team);
        return team;
    }

    public Team deleteLeader(String teamName, String googleName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException());
        userToAdd.deleteTeamLeader(team);
        userService.save(userToAdd);
        team.deleteLeader(userToAdd);
        mailService.sendMailDeleteUserFromTeam(userToAdd, team);
        return team;
    }

    public void deleteTeamByName(String teamName) {
        teamRepository.updateDelete(teamName, true);
    }


    public Team addGuestMemberToTeam(String teamName, String teamLeader, String userName, String email) {

        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException());
        teamValidation.validateGuestBeforePutMember(userName, email, team);
        User user = new User();
        user.setGoogleName(userName);
        user.setProvider(AuthProvider.local);
        user.setEmail(email);
        user.addTeamMember(team);
        userService.save(user);
        mailService.sendMailAddUserToTeamMember(user, team);
        team.addMember(user);
        return team;
    }
}