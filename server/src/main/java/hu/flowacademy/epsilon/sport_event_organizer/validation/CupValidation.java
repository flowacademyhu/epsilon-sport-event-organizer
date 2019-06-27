package hu.flowacademy.epsilon.sport_event_organizer.validation;

import hu.flowacademy.epsilon.sport_event_organizer.exception.ValidationException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CupValidation {

    @Autowired
    private CupRepository cupRepository;


    public void validateNameAndDatesBeforeSave(Cup cupToSave) {
        if (cupRepository.findByName(cupToSave.getName()).isPresent()) {
            throw new ValidationException("Cup is already exist with this name");
        }
        if (cupToSave.getEventDate() == null) {
            throw new ValidationException("Event date is missing");
        }

        if (cupToSave.getRegistrationEndDate() == null) {
            throw new ValidationException("Registration date is missing");
        }
    }

    public void validateCupOrganizerBeforeSave(Cup cupToSave) {
        if (cupToSave.getOrganizers().size() == 0) {
            throw new ValidationException("Cup has not got any Organizers");
        }
    }

    public void validateApplyTeam(String cupName, String teamName) {
        if (cupRepository.findByName(cupName).isEmpty()) {
            throw new ValidationException("Cup is missing");
        }
    }
}