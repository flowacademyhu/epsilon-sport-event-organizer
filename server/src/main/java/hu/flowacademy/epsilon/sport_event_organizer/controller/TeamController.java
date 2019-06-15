package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.service.TeamService;
import hu.flowacademy.epsilon.sport_event_organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

//
//    @PutMapping("/team-modify")
//    public ResponseEntity<Team> modifyTeam(@RequestBody Team team) {
//        return ResponseEntity.ok(teamService.update(team));
//    }
//
//    @GetMapping("/team/{name}")
//    public ResponseEntity<Optional<Team>> getTeam(@PathVariable String name) {
//        return ResponseEntity.ok(teamService.getTeamByName(name));
//    }
//
//    @GetMapping("/team-list")
//    public ResponseEntity<List<Team>> teamsList() {
//        return ResponseEntity.ok(teamService.getAllTeams());
//    }
//
//    @DeleteMapping("/team-delete/{name}")
//    public ResponseEntity<Void> deleteTeam(@PathVariable String name) {
//        teamService.deleteTeam(name);
//        return ResponseEntity.noContent().build();
//    }

    @PostMapping("/auth/team-create")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.save(team));
    }

    @GetMapping("/auth/get-team-member")
    public ResponseEntity<Team> getTeamMember() {
        return ResponseEntity.ok(teamService.getByMember());
    }

    @PutMapping("/auth/put-team-member/{teamName}")
    public ResponseEntity<Set<User>> putTeamMember(@PathVariable String teamName, @RequestBody User user) {
        return ResponseEntity.ok(teamService.putTeamMember(teamName, user.getEmail()));
    }


//    @GetMapping("/get-team-leader")
//    public ResponseEntity<Team> getTeamByLeader() {
//        return ResponseEntity.ok(teamService.getTeamByLeader());
//    }
}
