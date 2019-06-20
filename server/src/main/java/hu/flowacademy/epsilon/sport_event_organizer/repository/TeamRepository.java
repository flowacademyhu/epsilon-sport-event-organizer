package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    Optional<Team> findByName(String name);

    List<Team> findByCompany(String company);

    List<Team> findByUsers(User currentUser);

    List<Team> findByLeaders(User currentUser);

    @Modifying
    @Query("UPDATE Team t SET t.isDeleted = :isDeleted WHERE t.name = :name")
    void updateDelete(@Param("name") String name, @Param("isDeleted") boolean isDeleted);
}
