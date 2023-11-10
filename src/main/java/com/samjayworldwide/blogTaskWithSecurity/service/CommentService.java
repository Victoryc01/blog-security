package com.samjayworldwide.blogTaskWithSecurity.service;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.CommentRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.CommentResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.UserResponseDto;

import java.util.List;
import java.util.Map;

public interface CommentService {
    CommentResponseDto addACommentToAPost(CommentRequestDto commentRequestDto,Long postId,Long userId);
     void deleteAComment(Long commentId,Long userId);
     List<CommentResponseDto> getAllCommentsForAPost(Long postId);
     Map<CommentResponseDto, UserResponseDto> getAllCommentsAndTheUserByAPostId(Long postId);
}
