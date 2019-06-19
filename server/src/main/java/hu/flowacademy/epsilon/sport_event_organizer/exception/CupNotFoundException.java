package hu.flowacademy.epsilon.sport_event_organizer.exception;

public class CupNotFoundException extends RuntimeException {

    public CupNotFoundException(String string) {
        super("Could not find cup with " + string);
    }
}
