package hu.flowacademy.epsilon.sport_event_organizer.validation;

import hu.flowacademy.epsilon.sport_event_organizer.exception.ValidationException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.TeamRepository;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TeamValidation {

    public static final String TEAM_IS_ALREADY_EXIST_WITH_THIS_NAME = "validation.team.team_name_already_exist";
    public static final String TEAM_NAME_IS_MISSING = "validation.team.team_name_missing";
    public static final String TEAM_COMPANY_NAME_IS_MISSING = "validation.team.team_company_missing";
    public static final String USER_IS_ALREADY_A_MEMBER = "validation.team.user_is_already_a_member";
    public static final String USER_EMAIL_IS_MISSING = "validate.team.user_email_is_missing";
    public static final String USER_EMAIL_IS_FORBIDDEN = "validate.team.user_email_is_forbidden";
    public static final String USER_NAME_IS_MISSING = "validate.team.user_name_is_missing";
    public static final String USER_NAME_IS_FORBIDDEN = "validate.team.user_name_is_forbidden";


    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public void validateTeamNameBeforeSave(Team teamToSave) {
        if (teamRepository.findByName(teamToSave.getName()).isPresent()) {
            throw new ValidationException(TEAM_IS_ALREADY_EXIST_WITH_THIS_NAME);
        }
        if (teamToSave.getName().equals("") && teamToSave.getName() == null) {
            throw new ValidationException(TEAM_NAME_IS_MISSING);
        }

        if (teamToSave.getCompany().equals("") || teamToSave.getCompany() == null) {
            throw new ValidationException(TEAM_COMPANY_NAME_IS_MISSING);
        }
    }

    public void validateUserBeforePutMember(User userToAddMember, Team team) {
        if (team.getUsers().contains(userToAddMember)) {
            throw new ValidationException(USER_IS_ALREADY_A_MEMBER);
        }
    }


    public void validateGuestBeforePutMember(String userName, String email) {
        if (StringUtils.isEmpty(email)) {
            throw new ValidationException(USER_EMAIL_IS_MISSING);
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ValidationException(USER_EMAIL_IS_FORBIDDEN);
        }
        if (StringUtils.isEmpty(userName)) {
            throw new ValidationException(USER_NAME_IS_MISSING);
        }
        if (userRepository.findByGoogleName(userName).isPresent()) {
            throw new ValidationException(USER_NAME_IS_FORBIDDEN);
        }
    }


    public void validateTeamLeaderBeforeSave(Team teamToSave) {
        if (teamToSave.getLeaders().size() == 0) {
            throw new ValidationException("Team has not got any leader");
        }
    }


}
