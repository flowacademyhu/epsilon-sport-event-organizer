package hu.flowacademy.epsilon.sport_event_organizer.exception;

import java.util.function.Function;
import java.util.function.Supplier;

public class ExceptionCreator {

    public static Supplier<RuntimeException> createCupNotFoundException(String val) {
        return createException(val, CupNotFoundException::new);
    }

    private static Supplier<RuntimeException> createException(String val, Function<String, RuntimeException> consumer) {
        return () -> consumer.apply(val);
    }
}