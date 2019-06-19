package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.exception.UserNotFoundException;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import hu.flowacademy.epsilon.sport_event_organizer.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByID(UUID uuid) {
        return userRepository.findById(uuid);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> (UserPrincipal) principal)
                .map(UserPrincipal::getId)
                .flatMap(userRepository::findById).orElse(null);
    }

    public User findUserByGoogleName(String googleName) {
        return userRepository.findByGoogleName(googleName).orElseThrow(() -> new UserNotFoundException(googleName));
    }

    public void deleteUserByGoogleName(String googleName) {
        userRepository.updateDelete(googleName, true);
    }

}