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

    @GetMapping("/{name}")
    public ResponseEntity<Team> getTeam(@PathVariable String name) {
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.save(team));
    }

    @PutMapping
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.update(team));
    }


    @DeleteMapping("/{teamName}")
    public ResponseEntity<Void> deleteCup(@PathVariable String teamName) {
        teamService.deleteTeamByName(teamName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/member")
    public List<Team> getTeamByMember() {
        return teamService.getByCurrentMember();
    }

    @PutMapping("/member/{googleName}/{teamName}")
    public ResponseEntity<Team> putMember(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.putMember(teamName, googleName));
    }

    @DeleteMapping("/member/{googleName}/{teamName}")
    public ResponseEntity<Team> deleteMember(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.deleteMember(teamName, googleName));
    }

    @GetMapping("/leader")
    public List<Team> getTeamsByLeader() {
        return teamService.getByCurrentLeader();
    }

    @PutMapping("/leader/{googleName}/{teamName}")
    public ResponseEntity<Team> putLeader(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.putLeader(teamName, googleName));
    }

    @DeleteMapping("/leader/{googleName}/{teamName}")
    public ResponseEntity<Team> deleteLeader(@PathVariable String teamName, @PathVariable String googleName) {
        return ResponseEntity.ok(teamService.deleteLeader(teamName, googleName));
    }
}