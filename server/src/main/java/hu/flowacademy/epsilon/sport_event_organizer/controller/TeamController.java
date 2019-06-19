package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/get/{name}")
    public ResponseEntity<Team> getTeam(@PathVariable String name) {
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.save(team));
    }

    @PutMapping("/update")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.update(team));
    }

    @DeleteMapping("/delete/{teamName}")
    public ResponseEntity<Boolean> deleteCup(@PathVariable String teamName) {
        teamService.deleteTeamByName(teamName);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/get-by-current-member")
    public ResponseEntity<List<Team>> getTeamByMember() {
        return ResponseEntity.ok(teamService.getByCurrentMember());
    }

    @PutMapping("/put-member/{googleName}/{teamName}")
    public ResponseEntity<Set<User>> putMember(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.putMember(teamName, googleName));
    }

    @DeleteMapping("/delete-member/{googleName}/{teamName}")
    public ResponseEntity<Set<User>> deleteMember(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.deleteMember(teamName, googleName));
    }

    @GetMapping("/get-by-current-leader")
    public ResponseEntity<List<Team>> getTeamsByLeader() {
        return ResponseEntity.ok(teamService.getByCurrentLeader());
    }

    @PutMapping("/put-leader/{googleName}/{teamName}")
    public ResponseEntity<Set<User>> putLeader(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.putLeader(teamName, googleName));
    }

    @DeleteMapping("/delete-leader/{googleName}/{teamName}")
    public ResponseEntity<Team> deleteLeader(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.deleteLeader(teamName, googleName));
    }
}
