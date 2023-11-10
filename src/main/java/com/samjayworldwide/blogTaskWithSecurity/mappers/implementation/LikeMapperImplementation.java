package com.samjayworldwide.blogTaskWithSecurity.mappers.implementation;

import com.samjayworldwide.blogTaskWithSecurity.dto.response.LikeResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Likes;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LikeMapperImplementation implements Mapper<Likes, LikeResponseDto> {
    private ModelMapper modelMapper;

    public LikeMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LikeResponseDto mapTo(Likes likes) {
        return modelMapper.map(likes,LikeResponseDto.class);
    }

    @Override
    public Likes mapFrom(LikeResponseDto likeResponseDto) {
        return modelMapper.map(likeResponseDto, Likes.class);
    }
}
