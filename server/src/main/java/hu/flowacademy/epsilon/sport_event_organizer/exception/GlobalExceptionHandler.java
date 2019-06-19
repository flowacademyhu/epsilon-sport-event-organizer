package hu.flowacademy.epsilon.sport_event_organizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(UserNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String teamNotFoundHandler(TeamNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CupNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String cupNotFoundHandler(CupNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserForbidenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userForbiddenHandler(UserForbidenException e) {
        return e.getMessage();
    }


}
