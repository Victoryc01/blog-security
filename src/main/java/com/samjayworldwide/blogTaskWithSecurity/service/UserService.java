package com.samjayworldwide.blogTaskWithSecurity.service;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.UserRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.ApiResponse;
import com.samjayworldwide.blogTaskWithSecurity.exception.InvalidPasswordTypeException;

public interface UserService {
    ApiResponse<String> registerNewUser(UserRequestDto userRequestDto);
    ApiResponse<String> loginRegisteredUser (UserRequestDto userRequestDto);
    ApiResponse<String> registerAnAdmin(UserRequestDto userRequestDto);
    ApiResponse<String> loginRegisteredAdmin(UserRequestDto userRequestDto);
}
