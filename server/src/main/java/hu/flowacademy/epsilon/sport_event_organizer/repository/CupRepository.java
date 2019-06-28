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
import java.util.UUID;

@Repository
public interface CupRepository extends JpaRepository<Cup, String> {
    Optional<Cup> findByName(String cupName);

    List<Cup> findByOrganizers(User currentUser);

    @Modifying
    @Query("UPDATE Cup c SET c.isDeleted = :isDeleted WHERE c.name = :name")
    void updateDelete(@Param("name") String name, @Param("isDeleted") boolean isDeleted);

    List<Cup> findByPlace(String place);

    List<Cup> findByCompany(String company);

    Set<Cup> findByTeams(Team team);

    Set<Cup> findByApproved(Team team);

    @Query(value = "select cups.name as name, cups.company as company, cups.court_counter as court_counter, cups.description as description, cups.event_date as event_date, cups.image_url as image_url, cups.is_deleted as is_deleted, cups.place as place, cups.registration_end_date as registration_end_date, cups.sport_type as sport_type, cups.sports_name as sports_name from " +
            "(((cups inner join cups_approved on cups.name=cups_approved.cups_name) " +
            "inner join teams on teams.name=cups_approved.teams_name) " +
            "inner join teams_leaders on teams.name=teams_leaders.team_name) " +
            "left outer join teams_members on teams.name=teams_members.team_name " +
            "where teams_members.user_id=:id or teams_leaders.user_id=:id",
    nativeQuery = true)
    List<Cup> findCurrentUserInTeam(@Param("id") UUID id);
}
