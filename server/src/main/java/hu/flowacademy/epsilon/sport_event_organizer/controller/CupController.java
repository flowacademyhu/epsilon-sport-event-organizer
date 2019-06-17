package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/cups")
public class CupController {

    @Autowired
    private CupService cupService;

    @PostMapping("/create")
    public ResponseEntity<Cup> createCup(@RequestBody Cup team) {
        return ResponseEntity.ok(cupService.save(team));
    }

    @PutMapping("/update")
    public ResponseEntity<Cup> updateCup(@RequestBody Cup cup) {
        return ResponseEntity.ok(cupService.update(cup));
    }

    @GetMapping("/get/{cupName}")
    public ResponseEntity<Cup> getByCupName(@PathVariable String cupName) {
        return ResponseEntity.ok(cupService.getByCupName(cupName));
    }

    @GetMapping("/get-by-current-organizer")
    public ResponseEntity<Cup> getCupByOrganizer() {
        return ResponseEntity.ok(cupService.getByCurrentOrganizer());
    }

    @PutMapping("/put-member/{googleName}/{cupName}")
    public ResponseEntity<Set<User>> putOrganizer(@PathVariable String teamName, @PathVariable String cupName) {
        return ResponseEntity.ok(cupService.putOrganizer(teamName, cupName));
    }

    @DeleteMapping("/delete-member/{googleName}/{cupName}")
    public ResponseEntity<Set<User>> deleteOrganizer(@PathVariable String teamName, @PathVariable String cupName) {
        return ResponseEntity.ok(cupService.deleteOrganizer(teamName, cupName));
    }


}
