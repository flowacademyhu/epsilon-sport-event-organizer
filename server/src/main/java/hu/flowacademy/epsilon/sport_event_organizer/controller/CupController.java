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
    public ResponseEntity<Cup> getByCupName(@PathVariable String name) {
        return ResponseEntity.ok(cupService.getByName(name));
    }

    // returning all NON-DELETED cups!
    @GetMapping()
    public ResponseEntity<List<Cup>> getAllNonDeletedCups() {
        return ResponseEntity.ok(cupService.getAllNonDeletedCups());
    }

    @GetMapping("/with-deleted")
    public ResponseEntity<List<Cup>> getAllCups() {
        return ResponseEntity.ok(cupService.getAllCups());
    }

    @GetMapping("/{company}")
    public ResponseEntity<List<Cup>> getCupsByCompany(@PathVariable String company) {
        return ResponseEntity.ok(cupService.getCupsByCompany(company));
    }



    @PostMapping
    public ResponseEntity<Cup> createCup(@RequestBody Cup team) {
        return ResponseEntity.ok(cupService.save(team));
    }

    @PutMapping
    public ResponseEntity<Cup> updateCup(@RequestBody Cup cup) {
        return ResponseEntity.ok(cupService.update(cup));
    }

    @DeleteMapping("/{cupName}")
    public ResponseEntity<Void> deleteCup(@PathVariable String cupName) {
        cupService.deleteCupByName(cupName);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/organizer")
    public List<Cup> getCupByOrganizer() {
        return cupService.getByCurrentOrganizer();
    }

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
}
