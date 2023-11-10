package com.samjayworldwide.blogTaskWithSecurity.controller;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.UserRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.ApiResponse;
import com.samjayworldwide.blogTaskWithSecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private UserService userService;
     @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerANewUser(@RequestBody @Valid UserRequestDto userRequestDto){
         var response = userService.registerNewUser(userRequestDto);
         if (response.isResponseStatus()){
             return ResponseEntity.ok(response);
         }
         return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<ApiResponse<?>> registerAnAdmin(@RequestBody @Valid UserRequestDto userRequestDto){
         var response = userService.registerAnAdmin(userRequestDto);
         if (response.isResponseStatus()){
             return ResponseEntity.ok(response);
         } return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> logInARegisteredUser(@RequestBody @Valid UserRequestDto userRequestDto){
        var response = userService.loginRegisteredUser(userRequestDto);
        if (response.isResponseStatus()){
            return ResponseEntity.ok(response);
        }return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<ApiResponse<?>> loginARegisteredAdmin(@RequestBody @Valid UserRequestDto userRequestDto){
         var response = userService.loginRegisteredAdmin(userRequestDto);
        if (response.isResponseStatus()){
            return ResponseEntity.ok(response);
        }return ResponseEntity.badRequest().body(response);
    }
}
