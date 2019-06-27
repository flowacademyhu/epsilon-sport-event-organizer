package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.exception.ValidationException;
import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.repository.CupRepository;
import hu.flowacademy.epsilon.sport_event_organizer.validation.CupValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;


@RunWith(MockitoJUnitRunner.class)
public class CupValidateTest {

    @InjectMocks
    private CupValidation cupValidation;

    @Mock
    private CupRepository cupRepository;


    @Test(expected = ValidationException.class)
    public void whenCupIsAlreadyExist() {
        Cup cupToBeSaved = new Cup();
        cupToBeSaved.setName("Test Cup");
        Mockito.when(cupRepository.findByName(cupToBeSaved.getName())).thenReturn(java.util.Optional.of(cupToBeSaved));

        cupValidation.validateNameAndDatesBeforeSave(cupToBeSaved);
    }

    @Test(expected = ValidationException.class)
    public void whenMissingEventDate() {
        cupValidation.validateNameAndDatesBeforeSave(new Cup());
    }

    @Test(expected = ValidationException.class)
    public void whenMissingRegistrationEndDate() {
        Cup cup = new Cup();
        cup.setEventDate(LocalDate.now());
        cupValidation.validateNameAndDatesBeforeSave(cup);
    }
}