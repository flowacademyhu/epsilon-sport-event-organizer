package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/cup")
public class CupResource {

    @Autowired
    private CupService cupService;

    @GetMapping("/{name}")
    public ResponseEntity<Cup> getCupByName(@PathVariable String name) {
        return ResponseEntity.ok(cupService.getByName(name));
    }

    @GetMapping()
    public List<Cup> getAllCups() {
        return cupService.getAllCups();
    }

    @GetMapping("/company/{company}")
    public List<Cup> getAllCupsByCompany(@PathVariable String company) {
        return cupService.getCupsByCompany(company);
    }

    @GetMapping("/place/{place}")
    public List<Cup> getAllCupsByPlace(@PathVariable String place) {
        return cupService.getCupsByPlace(place);
    }

    @GetMapping("/my-cups")
    public List<Cup> getCupsByParticipation() {
        return cupService.getCupsByParticipation();
    }

    @PostMapping
    public ResponseEntity<Cup> createCup(@RequestBody Cup cup) {
        return ResponseEntity.ok(cupService.save(cup));
    }

    @PutMapping
    public ResponseEntity<Cup> updateCup(@RequestBody Cup cup) {
        return ResponseEntity.ok(cupService.update(cup));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteCup(@PathVariable String name) {
        cupService.deleteCupByName(name);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/apply/{cupName}/{teamName}")
    public ResponseEntity<Void> applyTeam(@PathVariable String cupName, @PathVariable String teamName) {
        cupService.applyTeam(cupName, teamName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/approve/{cupName}/{teamName}")
    public ResponseEntity<Void> approveTeam(@PathVariable String cupName, @PathVariable String teamName) {
        cupService.approveTeam(cupName, teamName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refuse/{cupName}/{teamName}")
    public ResponseEntity<Void> refuseTeam(@PathVariable String cupName, @PathVariable String teamName) {
        cupService.refuseTeam(cupName, teamName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cupName}/applied")
    public Set<Team> getAppliedTeams(@PathVariable String cupName) {
        return cupService.getAppliedTeams(cupName);
    }

    @GetMapping("/{cupName}/approved")
    public Set<Team> getApprovedTeams(@PathVariable String cupName) {
        return cupService.getApprovedTeams(cupName);
    }

    @GetMapping("/organizer")
    public List<Cup> getCupByOrganizer() {
        return cupService.getByCurrentOrganizer();
    }

    @GetMapping("/organizers/{cupName}")
    public Set<User> getOrganizers(@PathVariable String cupName) {
        return cupService.getOrganizers(cupName);
    }

    @PostMapping("/organizers/add/{cupName}/{googleName}")
    public ResponseEntity<Void> addOrganizer(@PathVariable String cupName, @PathVariable String googleName) {
        cupService.addOrganizer(cupName, googleName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/organizers/delete/{cupName}/{googleName}")
    public ResponseEntity<Void> deleteOrganizer(@PathVariable String cupName, @PathVariable String googleName) {
        cupService.deleteOrganizer(cupName, googleName);
        return ResponseEntity.noContent().build();
    }
}
