package com.samjayworldwide.blogTaskWithSecurity.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotEmpty(message = "this field cannot be empty")
    @NotBlank(message = "this field cannot be blank")
    @NotNull(message = "this field cannot be null")
    @Size( min = 2, max = 20 ,message = "firstname must contain a minimum of 2 characters and a Maximum of 20 characters")
    private String firstName;

    @NotEmpty(message = "this field cannot be empty")
    @NotBlank(message = "this field cannot be blank")
    @NotNull(message = "this field cannot be null")
    @Size( min = 2, max = 20 ,message = "lastname must contain a minimum of 2 characters and a Maximum of 20 characters")
    private String lastName;

    @NotEmpty(message = "this field cannot be empty")
    @NotBlank(message = "this field cannot be blank")
    @NotNull(message = "this field cannot be null")
    @Email(message = "this field must contain a valid email")
    private String email;

    @NotEmpty(message = "this field cannot be empty")
    @NotBlank(message = "this field cannot be blank")
    @NotNull(message = "this field cannot be null")
    @Size( min = 8, max = 20 ,message = "password must contain a minimum of 8 characters and a Maximum of 20 characters")
    private String password;

    @NotEmpty(message = "this field cannot be empty")
    @NotBlank(message = "this field cannot be blank")
    @NotNull(message = "this field cannot be null")
    @Size( min = 8, max = 20 ,message = "password must contain a minimum of 8 characters and a Maximum of 20 characters")
    private String retypePassword;
}
