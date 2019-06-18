package hu.flowacademy.epsilon.sport_event_organizer.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String string) {
        super("Could not find user with " + string);
    }
}
