package com.samjayworldwide.blogTaskWithSecurity.controller;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.PostRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.PostResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class AdminController {
    private PostService postService;
      @Autowired
    public AdminController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/writeAPost/{userId}")
    public ResponseEntity<PostResponseDto> writeAPost(@RequestBody PostRequestDto postRequestDto,
                                                      @PathVariable("userId") Long userId){
      return new ResponseEntity<>(postService.writeAPost(postRequestDto,userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAPost/{userId}/{postId}")
    public ResponseEntity deleteAPost(@PathVariable("userId") Long userId,@PathVariable("postId") Long postId){
        postService.deleteAPost(userId,postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
