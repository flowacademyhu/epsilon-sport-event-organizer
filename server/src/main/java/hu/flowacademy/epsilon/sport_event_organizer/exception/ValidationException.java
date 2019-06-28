package hu.flowacademy.epsilon.sport_event_organizer.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String string) {
        super("" + string);
    }
}
