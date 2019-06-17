package hu.flowacademy.epsilon.sport_event_organizer.repository;

import hu.flowacademy.epsilon.sport_event_organizer.model.AuthProvider;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenReturnUser() {
        User userToBeFound = new User();
        userToBeFound.setEmail("test@mail.com");
        userToBeFound.setProvider(AuthProvider.google);
        testEntityManager.persist(userToBeFound);
        testEntityManager.flush();
        Optional<User> userFound = userRepository.findByEmail(userToBeFound.getEmail());
        assertThat(userToBeFound.getEmail()).isEqualTo(userFound.map(User::getEmail).orElse(null));
    }

    @Test
    public void whenValidEmailSearched_thanReturnTrue() {
        User userWithEmail = new User();
        userWithEmail.setEmail("test@mail.com");
        userWithEmail.setProvider(AuthProvider.google);
        testEntityManager.persist(userWithEmail);
        testEntityManager.flush();
        assertTrue(userRepository.existsByEmail(userWithEmail.getEmail()));

    }

    @Test
    public void whenGoogleNameSearched_thanReturnUser() {
        User user = new User();
        user.setGoogleName("foo boo");
        user.setProvider(AuthProvider.google);
        testEntityManager.persist(user);
        testEntityManager.flush();
        Optional<User> userFound = userRepository.findByGoogleName(user.getGoogleName());
        assertThat(user.getGoogleName()).isEqualTo(userFound.map(User::getGoogleName).orElse(null));
    }

}
