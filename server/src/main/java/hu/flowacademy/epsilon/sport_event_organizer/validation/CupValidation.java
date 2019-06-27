package hu.flowacademy.epsilon.sport_event_organizer.validation;

import hu.flowacademy.epsilon.sport_event_organizer.exception.ValidationException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CupValidation {

    public static final String CUP_IS_ALREADY_EXIST_WITH_THIS_NAME = "validation.cup.already_exist";
    public static final String EVENT_DATE_IS_MISSING = "validation.cup.event_date_missing";
    public static final String REGISTRATION_DATE_IS_MISSING = "validation.cup.reg_date_missing";
    public static final String REGISTRATION_DATE_IS_AFTER_THE_EVENT = "validation.cup.reg_date_is_after_event";
    public static final String REGISTRATION_DATE_IS_IN_THE_PAST = "validation.cup.reg_date_is_in_the_past";
    public static final String EVENT_DATE_IS_IN_THE_PAST = "validation.cup.event_date_is_in_the_past";

    @Autowired
    private CupRepository cupRepository;


    public void validateNameAndDatesBeforeSave(Cup cupToSave) {
        if (cupRepository.findByName(cupToSave.getName()).isPresent()) {
            throw new ValidationException(CUP_IS_ALREADY_EXIST_WITH_THIS_NAME);
        }
        if (cupToSave.getEventDate() == null) {
            throw new ValidationException(EVENT_DATE_IS_MISSING);
        }

        if (cupToSave.getRegistrationEndDate() == null) {
            throw new ValidationException(REGISTRATION_DATE_IS_MISSING);
        }
        if (cupToSave.getRegistrationEndDate().isAfter(cupToSave.getEventDate())) {
            throw new ValidationException(REGISTRATION_DATE_IS_AFTER_THE_EVENT);
        }
        if (cupToSave.getRegistrationEndDate().isBefore(LocalDate.now())) {
            throw new ValidationException(REGISTRATION_DATE_IS_IN_THE_PAST);
        }
        if (cupToSave.getEventDate().isBefore(LocalDate.now())) {
            throw new ValidationException(EVENT_DATE_IS_IN_THE_PAST);
        }


    }


    public void validateCupOrganizerBeforeSave(Cup cupToSave) {
        if (cupToSave.getOrganizers().size() == 0) {
            throw new ValidationException("Cup has not got any Organizers");
        }
    }

}