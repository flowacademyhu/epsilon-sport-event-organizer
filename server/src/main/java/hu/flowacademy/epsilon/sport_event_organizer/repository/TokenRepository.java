package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

    Optional<Token> findByAccessToken(String accessToken);

}
