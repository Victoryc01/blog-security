package com.samjayworldwide.blogTaskWithSecurity.mappers.implementation;

import com.samjayworldwide.blogTaskWithSecurity.dto.response.UserResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImplementation implements Mapper<User,UserResponseDto> {
    private ModelMapper modelMapper;

    public UserMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDto mapTo(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public User mapFrom(UserResponseDto userResponseDto) {
        return modelMapper.map(userResponseDto, User.class);
    }
}
