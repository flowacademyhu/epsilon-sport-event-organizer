package hu.flowacademy.epsilon.sport_event_organizer.controller;

import hu.flowacademy.epsilon.sport_event_organizer.exception.oaut.ResourceNotFoundException;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import hu.flowacademy.epsilon.sport_event_organizer.security.CurrentUser;
import hu.flowacademy.epsilon.sport_event_organizer.security.UserPrincipal;
import hu.flowacademy.epsilon.sport_event_organizer.service.TeamService;
import hu.flowacademy.epsilon.sport_event_organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;


    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @DeleteMapping("/delete-team/{cupName}")
    public ResponseEntity<Void> deleteCup(@PathVariable String cupName) {
        userService.deleteCup(cupName);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/get-current")
    public ResponseEntity<User> getUserById() {
        return ResponseEntity.ok(userService.getCurrentUser().orElse(null));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/get/{googleName}")
    public ResponseEntity<User> getByGoogleName(@PathVariable String googleName) {
        return ResponseEntity.ok(userService.findUserByGoogleName(googleName));
    }


}
