package hu.flowacademy.epsilon.sport_event_organizer.service;

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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Team getTeamByName(String name) {
        return teamRepository.findByName(name).orElse(null);
    }


    public Team save(Team team) {
        User currentUser = userService.getCurrentUser().orElse(null);
        teamRepository.save(team);
        currentUser.addTeamLeader(team);
        User user = userRepository.save(currentUser);
        team.setLeader(user);
        return team;
    }

    public Team update(Team team) {
        if (teamRepository.findByName(team.getName()).isPresent()) {
            Team previousTeam = teamRepository.findByName(team.getName()).orElse(null);
            previousTeam.setName(team.getName());
            previousTeam.setCompany(team.getCompany());
            previousTeam.setImageUrl(team.getImageUrl());

            return teamRepository.save(previousTeam);
        }
        throw new NullPointerException();
        //TODO create custom exception for non existent team
    }

    public List<Team> getByCurrentMember() {
        User currentUser = userService.getCurrentUser().orElse(null);
        return teamRepository.findByUsers(currentUser);
    }

    public Set<User> putMember(String teamName, String googleName) {

        User user = userService.findUserByGoogleName(googleName).orElse(null);
        Team team = teamRepository.findByName(teamName).orElse(null);
        user.addTeamMember(team);
        userRepository.save(user);
        team.setUser(user);
        return team.getUsers();
    }


    public Set<User> deleteMember(String teamName, String googleName) {
        User user = userService.findUserByGoogleName(googleName).orElse(null);
        Team team = teamRepository.findByName(teamName).orElse(null);
        user.deleteTeamMember(team);
        userRepository.save(user);
        team.deleteUser(user);
        return team.getUsers();
    }

    public List<Team> getByCurrentLeader() {
        User currentUser = userService.getCurrentUser().orElse(null);
//        User currentUser = userService.findUserByGoogleName("Feri").orElse(null);
        return teamRepository.findByLeaders(currentUser);
    }

    public Set<User> putLeader(String teamName, String googleName) {
        User user = userService.findUserByGoogleName(googleName).orElse(null);
        Team team = teamRepository.findByName(teamName).orElse(null);
        user.addTeamLeader(team);
        userRepository.save(user);
        team.setLeader(user);
        return team.getUsers();
    }

    public Set<User> deleteLeader(String teamName, String googleName) {
        User user = userService.findUserByGoogleName(googleName).orElse(null);
        Team team = teamRepository.findByName(teamName).orElse(null);
        user.deleteTeamLeader(team);
        team.deleteLeader(user);
        userRepository.save(user);
        return team.getUsers();
    }

}
