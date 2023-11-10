package com.samjayworldwide.blogTaskWithSecurity.mappers.implementation;

import com.samjayworldwide.blogTaskWithSecurity.dto.response.PostResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Post;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImplementation implements Mapper<Post, PostResponseDto> {
    private ModelMapper modelMapper;

    public PostMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostResponseDto mapTo(Post post) {
        return modelMapper.map(post,PostResponseDto.class);
    }

    @Override
    public Post mapFrom(PostResponseDto postResponseDto) {
        return modelMapper.map(postResponseDto, Post.class);
    }
}
