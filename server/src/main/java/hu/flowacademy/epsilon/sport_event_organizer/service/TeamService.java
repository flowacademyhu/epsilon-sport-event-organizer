package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    public Team getByMember() {
        User currentUser = userService.getCurrentUser().orElse(null);
        return teamRepository.findByUsers(currentUser).orElse(null);
    }


    public Team save(Team team) {
//        User currentUser = userService.getCurrentUser().orElse(null);
//        team.setLeaders(currentUser);
        return teamRepository.save(team);
    }

    public Set<User> putTeamMember(String teamName, String email) {

        User user = userService.findUserByEmail(email).orElse(null);
        System.err.println(user);
        Team team = teamRepository.findByName(teamName).orElse(null);
        team.setUsers(user);
        teamRepository.save(team);
        return team.getUsers();
    }
//
//    public Team update(Team team) {
//        if (teamRepository.findByName(team.getName()).isPresent()) {
//            return teamRepository.save(team);
//        }
//        throw new NullPointerException();
//        //TODO create custom exception for non existant team
//    }
//
//    public Optional<Team> getTeamByName(String name) {
//        return teamRepository.findByName(name);
//    }
//
//    public Optional<Team> getTeamByCompany(String company) {
//        return teamRepository.findByCompany(company);
//    }
//
//    public List<Team> getAllTeams() {
//        return teamRepository.findAll();
//    }
//
//    public void deleteTeam(String name) {
//        teamRepository.deleteById(name);
//    }
//
//    //TODO create query for getting team(s) by user (?)
//


}
