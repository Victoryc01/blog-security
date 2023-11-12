package com.samjayworldwide.blogTaskWithSecurity.repository;

import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testIfUserRepositorySavesUserToTheDatabase(){
        User user = User
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();
        User savedUser = userRepository.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testIfUserRepositoryCanFindAUserByAGivenId(){
        User user = User
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();
        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(user.getId());
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(foundUser).isPresent();
    }
}
