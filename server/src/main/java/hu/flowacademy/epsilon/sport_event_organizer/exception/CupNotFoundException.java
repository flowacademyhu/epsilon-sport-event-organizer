package hu.flowacademy.epsilon.sport_event_organizer.exception;

public class CupNotFoundException extends RuntimeException {

    public static final String CUP_NOT_EXIST_WITH_THIS_NAME = "validation.cup.cup_not_exist_with_this_name";


    public CupNotFoundException(String string) {
        super(CUP_NOT_EXIST_WITH_THIS_NAME);
    }
}
