package com.samjayworldwide.blogTaskWithSecurity.service;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.UserRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.ApiResponse;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import com.samjayworldwide.blogTaskWithSecurity.repository.UserRepository;
import com.samjayworldwide.blogTaskWithSecurity.serviceImplementation.UserServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImplementation userService;

    @Test
    public void testIfUserServiceAddsAUserToADatabase(){
        User user = User
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();

        UserRequestDto userRequestDto = UserRequestDto
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();


        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(passwordEncoder.encode("samuelJackson")).thenReturn(userRequestDto.getPassword());

        ApiResponse<String> response = userService.registerNewUser(userRequestDto);

        Assertions.assertThat(response.getResponseMessage()).isEqualTo("congratulations account has been created");
    }

}
