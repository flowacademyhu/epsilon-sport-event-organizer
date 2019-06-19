package hu.flowacademy.epsilon.sport_event_organizer.exception;


public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String string) {
        super("Could not find team with " + string);
    }
}
