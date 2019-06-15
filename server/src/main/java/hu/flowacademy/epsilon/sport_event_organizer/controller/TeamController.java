package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.service.TeamService;
import hu.flowacademy.epsilon.sport_event_organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/auth")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    //
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
    @PostMapping("/create")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.save(team));
    }

    @PutMapping("/team-modify")
    public ResponseEntity<Team> modifyTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.update(team));
    }

    @GetMapping("/get-by-member")
    public ResponseEntity<Team> getTeamByMember() {
        return ResponseEntity.ok(teamService.getByMember());
    }

    @PutMapping("/put-member/{googleName}/{teamName}")
    public ResponseEntity<Set<User>> putMember(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.putMember(teamName, googleName));
    }

    @DeleteMapping("/delete-member/{googleName}/{teamName}")
    public ResponseEntity<Set<User>> deleteMember(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.deleteMember(teamName, googleName));
    }

    @GetMapping("/get-by-leader")
    public ResponseEntity<Team> getTeamByLeader() {
        return ResponseEntity.ok(teamService.getByMember());
    }

    @PutMapping("/put-leader/{googleName}/{teamName}")
    public ResponseEntity<Set<User>> putLeader(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.putLeader(teamName, googleName));
    }

    @DeleteMapping("/delete-leader/{googleName}/{teamName}")
    public ResponseEntity<Set<User>> deleteLeader(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.deleteLeader(teamName, googleName));
    }


//    @GetMapping("/get-team-leader")
//    public ResponseEntity<Team> getTeamByLeader() {
//        return ResponseEntity.ok(teamService.getTeamByLeader());
//    }
}
