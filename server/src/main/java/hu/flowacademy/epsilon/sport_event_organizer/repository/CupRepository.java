package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CupRepository extends JpaRepository<Cup, String> {
    Optional<Cup> findByName(String cupName);

    List<Cup> findByOrganizers(User currentUser);
}
