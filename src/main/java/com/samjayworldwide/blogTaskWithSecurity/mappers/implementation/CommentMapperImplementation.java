package com.samjayworldwide.blogTaskWithSecurity.mappers.implementation;

import com.samjayworldwide.blogTaskWithSecurity.dto.response.CommentResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Comment;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImplementation implements Mapper<Comment, CommentResponseDto> {
    private ModelMapper modelMapper;


    public CommentMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentResponseDto mapTo(Comment comment) {
        return modelMapper.map(comment,CommentResponseDto.class);
    }

    @Override
    public Comment mapFrom(CommentResponseDto commentResponseDto) {
        return modelMapper.map(commentResponseDto,Comment.class);
    }
}
