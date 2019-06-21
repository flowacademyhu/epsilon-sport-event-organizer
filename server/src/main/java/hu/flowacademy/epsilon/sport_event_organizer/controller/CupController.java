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
public class CupController {

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

    @GetMapping("/organizer")
    public ResponseEntity<List<Cup>> getCupByOrganizer() {
        return ResponseEntity.ok(cupService.getByCurrentOrganizer());
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

    /*
    @PutMapping("/organizer/{googleName}/{cupName}")
    public Set<User> putOrganizer(@PathVariable String googleName, @PathVariable String cupName) {
        return cupService.putOrganizer(googleName, cupName);
    }

    @DeleteMapping("/organizer/{googleName}/{cupName}")
    public Set<User> deleteOrganizer(@PathVariable String googleName, @PathVariable String cupName) {
        return cupService.deleteOrganizer(googleName, cupName);
    }

    @PutMapping("/team/{teamName}/{cupName}")
    public Set<Team> putTeam(@PathVariable String teamName, @PathVariable String cupName) {
        return cupService.putTeam(teamName, cupName);
    }

    @DeleteMapping("/team/{teamName}/{cupName}")
    public Set<Team> deleteTeam(@PathVariable String teamName, @PathVariable String cupName) {
        return cupService.deleteTeam(teamName, cupName);
    }

     */
}
