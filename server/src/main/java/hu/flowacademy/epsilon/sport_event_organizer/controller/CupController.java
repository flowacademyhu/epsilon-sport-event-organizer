package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
