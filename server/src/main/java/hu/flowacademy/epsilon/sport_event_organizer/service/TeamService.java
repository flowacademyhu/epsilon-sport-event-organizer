package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.exception.TeamNotFoundException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    public Team getTeamByName(String teamName) {
        return teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
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

    public List<Team> getByCurrentMember() {
        User currentUser = userService.getCurrentUser();
        return teamRepository.findByUsers(currentUser);
    }

    public Set<User> putMember(String teamName, String googleName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        if (team.getLeaders().contains(userToAdd)) {
            throw new RuntimeException();
            //TODO create normal exception
        }
        userToAdd.addTeamMember(team);
        userService.save(userToAdd);
        team.addMember(userToAdd);
        Set<User> users = team.getUsers();
        users.removeIf(User::isDeleted);
        return users;
    }


    public Set<User> deleteMember(String teamName, String googleName) {
        User userToRemove = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        userToRemove.deleteTeamMember(team);
        userService.save(userToRemove);
        team.deleteMember(userToRemove);
        Set<User> users = team.getUsers();
        users.removeIf(User::isDeleted);
        return users;

    }

    public List<Team> getByCurrentLeader() {
        User currentUser = userService.getCurrentUser();
        return teamRepository.findByLeaders(currentUser);
    }

    public Set<User> putLeader(String teamName, String googleName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        if (team.getLeaders().contains(userToAdd)) {
            throw new RuntimeException();
            //TODO create normal exception
        }
        userToAdd.addTeamLeader(team);
        userService.save(userToAdd);
        team.addLeader(userToAdd);
        Set<User> users = team.getLeaders();
        users.removeIf(User::isDeleted);
        return users;
    }

    public Set<User> deleteLeader(String teamName, String googleName) {
        User userToAdd = userService.findUserByGoogleName(googleName);
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        userToAdd.deleteTeamLeader(team);
        userService.save(userToAdd);
        team.deleteLeader(userToAdd);
        Set<User> users = team.getLeaders();
        System.err.println(users.size());
        users.removeIf(User::isDeleted);
        System.err.println(users.size());
        return users;
    }

    public void deleteTeamByName(String teamName) {
//        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
//        team.setDeleted(true);
        teamRepository.updateDelete(teamName, true);
    }

}