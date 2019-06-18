package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
    }

    @Test
    public void whenUserToBeSaved_thenReturnUSerSaved() {
        User userToBeSaved = new User();
        User userSaved = userService.save(userToBeSaved);
        assertEquals(userToBeSaved, userSaved);
    }

}
