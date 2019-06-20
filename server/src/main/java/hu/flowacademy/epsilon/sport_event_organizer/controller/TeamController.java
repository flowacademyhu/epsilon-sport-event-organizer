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

    @GetMapping("/{names}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String names) {
        return ResponseEntity.ok(teamService.getTeamByName(names));
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllNonDeletedTeams());
    }

    @GetMapping("/company/{companies}")
    public ResponseEntity<List<Team>> getAllTeamsByCompany(@PathVariable String companies) {
        return ResponseEntity.ok(teamService.getAllTeamsByCompany(companies));
    }

    @GetMapping("/member")
    public ResponseEntity<List<Team>> getAllTeamsByMember() {
        return ResponseEntity.ok(teamService.getByCurrentUser());
    }

    @GetMapping("/leader")
    public ResponseEntity<List<Team>> getAllTeamsByLeader() {
        return ResponseEntity.ok(teamService.getByCurrentLeader());
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.save(team));
    }

    @PutMapping
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.update(team));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String name) {
        teamService.deleteTeamByName(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/member/{teamName}/{googleName}")
    public ResponseEntity<Team> putMember(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.putMember(teamName, googleName));
    }

    @DeleteMapping("/member/{teamName}/{googleName}")
    public ResponseEntity<Team> deleteMember(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.deleteMember(teamName, googleName));
    }

    @PutMapping("/leader/{teamName}/{googleName}")
    public ResponseEntity<Team> putLeader(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.putLeader(teamName, googleName));
    }

    @DeleteMapping("/leader/{teamName}/{googleName}")
    public ResponseEntity<Team> deleteLeader(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.deleteLeader(teamName, googleName));
    }
}