package com.samjayworldwide.blogTaskWithSecurity.service;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.PostRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto writeAPost(PostRequestDto postRequestDto, Long userId);
    List<PostResponseDto> findAPost(String postCategory);
    void deleteAPost(Long userId, Long postId);
}
