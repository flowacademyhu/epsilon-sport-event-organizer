package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, String> {

    Optional<Team> findByName(String name);

    List<Team> findByCompany(String company);
}
