package com.samjayworldwide.blogTaskWithSecurity.serviceImplementation;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.UserRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.ApiResponse;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import com.samjayworldwide.blogTaskWithSecurity.enums.Roles;
import com.samjayworldwide.blogTaskWithSecurity.exception.InvalidEmailException;
import com.samjayworldwide.blogTaskWithSecurity.exception.InvalidLoginDetailsException;
import com.samjayworldwide.blogTaskWithSecurity.exception.InvalidPasswordTypeException;
import com.samjayworldwide.blogTaskWithSecurity.repository.UserRepository;
import com.samjayworldwide.blogTaskWithSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.samjayworldwide.blogTaskWithSecurity.enums.Roles.ADMIN;
import static com.samjayworldwide.blogTaskWithSecurity.enums.Roles.USER;

@Service
public class UserServiceImplementation implements UserService{
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
      @Autowired
    public UserServiceImplementation(PasswordEncoder passwordEncoder,
                                     UserRepository userRepository,
                                     AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ApiResponse<String> registerNewUser(UserRequestDto userRequestDto) {
        if (userRepository.findFirstByEmail(userRequestDto.getEmail()).isPresent()){
            throw new InvalidEmailException("this email already exists please type in a new email or proceed to Login", HttpStatus.CONFLICT);
        }
        if (!userRequestDto.getPassword().equals(userRequestDto.getRetypePassword())){
            throw new InvalidPasswordTypeException("passwords do not match",HttpStatus.BAD_REQUEST);
        }
        var newuser = User
                .builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .role(USER.name())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        userRepository.save(newuser);


        return new ApiResponse<>("congratulations account has been created",true,"");
    }

    @Override
    public ApiResponse<String> loginRegisteredUser(UserRequestDto userRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequestDto.getEmail(),
                userRequestDto.getPassword()
        ));
       User loggedInUser = userRepository.findByEmailAndPassword(userRequestDto.getEmail(), userRequestDto.getPassword())
                .orElseThrow(()-> new InvalidLoginDetailsException("check your email and password and try again",HttpStatus.BAD_REQUEST));

        return new ApiResponse<>("login Successful WELCOME "+loggedInUser.getFirstName(),true,"");

    }

    @Override
    public ApiResponse<String> registerAnAdmin(UserRequestDto userRequestDto) {
        if (userRepository.findFirstByEmail(userRequestDto.getEmail()).isPresent()){
            throw new InvalidEmailException("this email already exists please type in a new email or proceed to Login", HttpStatus.CONFLICT);
        }
        if (!userRequestDto.getPassword().equals(userRequestDto.getRetypePassword())){
            throw new InvalidPasswordTypeException("passwords do not match",HttpStatus.BAD_REQUEST);
        }



        UserDetails adminUser = org.springframework.security.core.userdetails.User.builder()
                .username(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .roles(ADMIN.name())
                .build();


        var newAdmin = User
                .builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .role(adminUser.getAuthorities().iterator().next().getAuthority())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        userRepository.save(newAdmin);

        return new ApiResponse<>("congratulations account has been created",true,"");
    }

    @Override
    public ApiResponse<String> loginRegisteredAdmin(UserRequestDto userRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequestDto.getEmail(),
                userRequestDto.getPassword()
        ));
       User loggedInAdmin =  userRepository.findByEmailAndPassword(userRequestDto.getEmail(), userRequestDto.getPassword())
                .orElseThrow(()-> new InvalidLoginDetailsException("check your email and password and try again",HttpStatus.BAD_REQUEST));

        return new ApiResponse<>("login Successful WELCOME "+loggedInAdmin.getFirstName(),true,"");
    }

}
