package com.samjayworldwide.blogTaskWithSecurity.controller;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.CommentRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.request.LikeRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.CommentResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.LikeResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.PostResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.UserResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.service.CommentService;
import com.samjayworldwide.blogTaskWithSecurity.service.LikeService;
import com.samjayworldwide.blogTaskWithSecurity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private CommentService commentService;
    private PostService postService;
    private LikeService likeService;
       @Autowired
    public UserController(CommentService commentService,
                          PostService postService,
                          LikeService likeService) {
        this.commentService = commentService;
        this.postService = postService;
        this.likeService = likeService;
    }

    @PostMapping(path = "/addAComment/{postId}/{userId}")
    public ResponseEntity<CommentResponseDto> addACommentToAPost(@RequestBody CommentRequestDto commentRequestDto,
                                                                @PathVariable("postId") Long postId,
                                                                @PathVariable("userId") Long userId){
      return new ResponseEntity<>(commentService.addACommentToAPost(commentRequestDto,postId,userId), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteAComment/{commentId}/{userId}")
    public ResponseEntity deleteAComment(@PathVariable("commentId") Long commentId,
                               @PathVariable("userId") Long userId){
        commentService.deleteAComment(commentId,userId);
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/viewComments/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsInAPost(@PathVariable("postId") Long postId){
          return new ResponseEntity<>(commentService.getAllCommentsForAPost(postId),HttpStatus.OK);
    }

    @PostMapping(path = "/search/{postCategory}")
    public ResponseEntity<List<PostResponseDto>> searchForAPostByPostCategory(@PathVariable("postCategory") String postCategory){
        return new ResponseEntity<>(postService.findAPost(postCategory),HttpStatus.FOUND);
    }

    @PostMapping(path = "/addALike/{postId}/{userId}")
    public ResponseEntity<LikeResponseDto> addALikeToAPost(@RequestBody LikeRequestDto likeRequestDto,
                                           @PathVariable("postId") Long postId,
                                           @PathVariable("userId") Long userId){
        return new ResponseEntity<>(likeService.addALikeToAPost(likeRequestDto,postId,userId),HttpStatus.CREATED);
    }

    @GetMapping(path = "/viewCommentsAndUsers/{postId}")
    public ResponseEntity<Map<CommentResponseDto, UserResponseDto>> getAllCommentsWithUsers(@PathVariable("postId") Long postId){
           return new ResponseEntity<>(commentService.getAllCommentsAndTheUserByAPostId(postId),HttpStatus.OK);

    }


}
