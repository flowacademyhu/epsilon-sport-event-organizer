package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import hu.flowacademy.epsilon.sport_event_organizer.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestConfig {

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() {
        User user = new User();
        user.setGoogleName("Foo Boo");
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findByGoogleName(user.getGoogleName())).thenReturn(java.util.Optional.of(user));
    }

    @Test
    public void whenUserToBeSaved_thenReturnUSerSaved() {
        User userToBeSaved = new User();
        userToBeSaved.setGoogleName("Foo Boo");
        User userSaved = userService.save(userToBeSaved);
        assertEquals(userToBeSaved, userSaved);
    }

    @Test
    public void whenGoogleNameSearched_thenReturnUser() {
        String googleName = "Foo Boo";
        User userFound = userService.findUserByGoogleName(googleName);
        assertTrue(userFound.getGoogleName().equalsIgnoreCase(googleName));

    }

    @Test
    public void whenDeleteByGoogleName_thenDelete() {
        User userToDelete = new User();
        userToDelete.setGoogleName("Foo Boo");
        String googleName = "Foo Boo";
        userService.deleteUserByGoogleName(googleName);
        assertFalse(userToDelete.isDeleted());
    }
}
