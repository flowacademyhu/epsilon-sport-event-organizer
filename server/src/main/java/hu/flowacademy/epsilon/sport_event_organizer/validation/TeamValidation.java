package hu.flowacademy.epsilon.sport_event_organizer.validation;

import hu.flowacademy.epsilon.sport_event_organizer.exception.ValidationException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamValidation {

    public static final String TEAM_IS_ALREADY_EXIST_WITH_THIS_NAME = "validation.team.team_name_already_exist";
    public static final String TEAM_NAME_IS_MISSING = "validation.team.team_name_missing";
    public static final String TEAM_COMPANY_NAME_IS_MISSING = "validation.team.team_company_missing";


    @Autowired
    private TeamRepository teamRepository;

    public void validateTeamNameBeforeSave(Team teamToSave) {
        if (teamRepository.findByName(teamToSave.getName()).isPresent()) {
            throw new ValidationException(TEAM_IS_ALREADY_EXIST_WITH_THIS_NAME);
        }
        if (teamToSave.getName().equals("") || teamToSave.getName() == null) {
            throw new ValidationException(TEAM_NAME_IS_MISSING);
        }

        if (teamToSave.getCompany().equals("") || teamToSave.getCompany() == null) {
            throw new ValidationException(TEAM_COMPANY_NAME_IS_MISSING);
        }
    }

    public void validateUserBeforePutMember(Team userToAddMember) {


    }


    public void validateTeamLeaderBeforeSave(Team teamToSave) {
        if (teamToSave.getLeaders().size() == 0) {
            throw new ValidationException("Team has not got any leader");
        }
    }


}
