package hu.flowacademy.epsilon.sport_event_organizer.validation;

import hu.flowacademy.epsilon.sport_event_organizer.exception.ValidationException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamValidation {

    public static final String TEAM_IS_ALREADY_EXIST_WITH_THIS_NAME = "validation.team.team_name_already_exist";


    @Autowired
    private TeamRepository teamRepository;

    public void validateTeamNameBeforeSave(Team teamToSave) {
        if (teamRepository.findByName(teamToSave.getName()).isPresent()) {
            throw new ValidationException(TEAM_IS_ALREADY_EXIST_WITH_THIS_NAME);
        }
    }

    public void validateTeamLeaderBeforeSave(Team teamToSave) {
        if (teamToSave.getLeaders().size() == 0) {
            throw new ValidationException("Team has not got any leader");
        }
    }


}
