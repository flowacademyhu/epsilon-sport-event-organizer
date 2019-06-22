package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CupRepository extends JpaRepository<Cup, String> {
    Optional<Cup> findByName(String cupName);

    List<Cup> findByOrganizers(User currentUser);

    @Modifying
    @Query("UPDATE Cup c SET c.isDeleted = :isDeleted WHERE c.name = :name")
    void updateDelete(@Param("name") String name, @Param("isDeleted") boolean isDeleted);

    List<Cup> findByPlace(String place);

    List<Cup> findByCompany(String company);

}
