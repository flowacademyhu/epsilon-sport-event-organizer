package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.email.MailService;
import hu.flowacademy.epsilon.sport_event_organizer.exception.TeamNotFoundException;
import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
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


    public Team getTeamByName(String teamName) {
        return teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
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
        User currentUser = userService.getCurrentUser();
        team.setDeleted(false);
        teamRepository.save(team);
        currentUser.addTeamLeader(team);
        User user = userService.save(currentUser);
        team.addLeader(user);
        return team;
    }

    public Team update(Team team) {
        Team previousTeam = teamRepository.findByName(team.getName()).orElseThrow(() -> new TeamNotFoundException(team.getName()));
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
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        userToAdd.addTeamMember(team);
        userService.save(userToAdd);
        team.addMember(userToAdd);
//        Set<User> users = team.getMembers();
//        users.removeIf(User::isDeleted);
//        return users;
        mailService.sendMailAddUserToTeamMember(userToAdd, team);
        return team;
    }


    public Team deleteMember(String teamName, String googleName) {
        User userToRemove = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        userToRemove.deleteTeamMember(team);
        userService.save(userToRemove);
        team.deleteMember(userToRemove);
        mailService.sendMailDeleteUserFromTeam(userToRemove, team);
//        Set<User> users = team.getMembers();
//        users.removeIf(User::isDeleted);
//        return users;
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
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        if (team.getUsers().contains(userToAdd)) {
            team.getUsers().remove(userToAdd);
            userToAdd.deleteTeamMember(team);
        }
        userToAdd.addTeamLeader(team);
        userService.save(userToAdd);
        team.addLeader(userToAdd);
        mailService.sendMailAddUserToTeamLeader(userToAdd, team);
//        Set<User> users = team.getLeaders();
//        users.removeIf(User::isDeleted);
//        return users;
        return team;
    }

    public Team deleteLeader(String teamName, String googleName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        userToAdd.deleteTeamLeader(team);
        userService.save(userToAdd);
        team.deleteLeader(userToAdd);
        mailService.sendMailDeleteUserFromTeam(userToAdd, team);
//        Set<User> users = team.getLeaders();
//        users.removeIf(user -> user.isDeleted());
//        users.removeIf(User::isDeleted);
//        return users;
        return team;
    }

    public void deleteTeamByName(String teamName) {
        teamRepository.updateDelete(teamName, true);
    }


    public Team addGuestMemberToTeam(String teamName, String teamLeader, String name, String email) {
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        User user = new User();
        user.setGoogleName(name);
        user.setProvider(AuthProvider.local);
        user.setEmail(email);
        user.addTeamMember(team);
        userService.save(user);
        mailService.sendMailAddUserToTeamMember(user, team);
        team.addMember(user);
        return team;
    }
}