package hu.flowacademy.epsilon.sport_event_organizer.exception;


public class TeamNotFoundException extends RuntimeException {

    public static final String TEAM_NOT_EXIST_WITH_THIS_NAME = "validation.team.team_not_exist_with_this_name";


    public TeamNotFoundException() {
        super(TEAM_NOT_EXIST_WITH_THIS_NAME);
    }
}
