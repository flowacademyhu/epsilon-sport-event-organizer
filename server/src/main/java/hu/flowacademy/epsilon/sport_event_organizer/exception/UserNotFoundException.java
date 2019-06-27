package hu.flowacademy.epsilon.sport_event_organizer.exception;

public class UserNotFoundException extends RuntimeException {

    public static final String USER_NOT_EXIST_WITH_THIS_NAME = "validation.user.user_not_exist_with_this_name";

    public UserNotFoundException() {
        super(USER_NOT_EXIST_WITH_THIS_NAME);
    }
}
