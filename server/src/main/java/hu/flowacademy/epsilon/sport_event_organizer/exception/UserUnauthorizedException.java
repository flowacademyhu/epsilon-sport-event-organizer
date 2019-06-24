package hu.flowacademy.epsilon.sport_event_organizer.exception;

public class UserUnauthorizedException extends RuntimeException {
    public UserUnauthorizedException() {
        super("User unauthorized for request!");
    }
}
