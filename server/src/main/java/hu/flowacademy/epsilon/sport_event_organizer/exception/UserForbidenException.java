package hu.flowacademy.epsilon.sport_event_organizer.exception;

public class UserForbidenException extends RuntimeException {
    public UserForbidenException(String string) {
        super("User is forbidden with " + string);
    }


}
