package com.samjayworldwide.blogTaskWithSecurity.service;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.LikeRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.LikeResponseDto;

public interface LikeService {
    LikeResponseDto addALikeToAPost(LikeRequestDto likeRequestDto,Long postId, Long userId);
}
