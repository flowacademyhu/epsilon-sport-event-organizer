package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.model.Match;
import hu.flowacademy.epsilon.sport_event_organizer.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/match")
public class MatchResource {
//    @Autowired
//    private MatchService matchService;
//
//    @GetMapping("/groups/{cupName}")
//    public List<Match> getGroupMatches(@PathVariable String cupName) {
//        return matchService.generateGroupMatches(cupName);
//    }
}
