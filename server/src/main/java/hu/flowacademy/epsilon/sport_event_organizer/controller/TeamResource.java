package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/team")
public class TeamResource {

    @Autowired
    private TeamService teamService;

    @GetMapping("/{names}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String names) {
        return ResponseEntity.ok(teamService.getTeamByName(names));
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllNonDeletedTeams();
    }

    @GetMapping("/company/{companies}")
    public List<Team> getAllTeamsByCompany(@PathVariable String companies) {
        return teamService.getAllTeamsByCompany(companies);
    }

    @GetMapping("/member")
    public List<Team> getAllTeamsByMember() {
        return teamService.getByCurrentUser();
    }

    @GetMapping("/leader")
    public List<Team> getAllTeamsByLeader() {
        return teamService.getByCurrentLeader();
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

    @PostMapping("/member/guest/{teamName}/{teamLeader}/{guestName}/{guestEmail}")
    public ResponseEntity<Team> putGuestMember(@PathVariable String teamName, @PathVariable String guestName, @PathVariable String teamLeader, @PathVariable String guestEmail) {
        return ResponseEntity.ok(teamService.addGuestMemberToTeam(teamName, teamLeader, guestName, guestEmail));
    }

}