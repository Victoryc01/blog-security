package com.samjayworldwide.blogTaskWithSecurity.serviceImplementation;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.CommentRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.CommentResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.UserResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Comment;
import com.samjayworldwide.blogTaskWithSecurity.entity.Post;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import com.samjayworldwide.blogTaskWithSecurity.exception.CommentNotFoundException;
import com.samjayworldwide.blogTaskWithSecurity.exception.PostNotFoundException;
import com.samjayworldwide.blogTaskWithSecurity.exception.UserNotFoundException;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import com.samjayworldwide.blogTaskWithSecurity.repository.CommentRepository;
import com.samjayworldwide.blogTaskWithSecurity.repository.PostRepository;
import com.samjayworldwide.blogTaskWithSecurity.repository.UserRepository;
import com.samjayworldwide.blogTaskWithSecurity.service.CommentService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    private Mapper<Comment,CommentResponseDto> commentMapper;
    private Mapper<User,UserResponseDto> userMapper;
      @Autowired
    public CommentServiceImplementation(CommentRepository commentRepository,
                                        UserRepository userRepository,
                                        PostRepository postRepository,
                                        Mapper<Comment,CommentResponseDto> commentMapper,
                                        Mapper<User,UserResponseDto> userMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }


    @Override
    public CommentResponseDto addACommentToAPost(CommentRequestDto commentRequestDto, Long postId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("user with " + userId +" not found", HttpStatus.NOT_FOUND);
        }


        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            throw new PostNotFoundException("post with " + postId + " not found",HttpStatus.NOT_FOUND);
        }

        Comment comment = Comment
                .builder()
                .message(commentRequestDto.getMessage())
                .build();

        user.get().addComments(comment);
        post.get().addCommentToPost(comment);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.mapTo(savedComment);


    }

    @Override
    public void deleteAComment(Long commentId, Long userId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw new UserNotFoundException("user with " + userId +" not found", HttpStatus.NOT_FOUND);
        }
        if (comment.isEmpty()){
            throw new CommentNotFoundException("comment does not exists again",HttpStatus.NOT_FOUND);
        }
        user.get().removeComments(comment.get());
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentResponseDto> getAllCommentsForAPost(Long postId) {
        boolean postExists = postRepository.findById(postId).isPresent();
        if (postExists){
            List<Comment> comment = commentRepository.findCommentsByPostId(postId);
            return comment.stream().map(commentMapper::mapTo).toList();

        }else{
             throw new PostNotFoundException("post cannot be found", HttpStatus.NOT_FOUND);

        }

    }

    @Override
    public Map<CommentResponseDto,UserResponseDto> getAllCommentsAndTheUserByAPostId(Long postId) {
        boolean postExists = postRepository.findById(postId).isPresent();
        Map<CommentResponseDto, UserResponseDto> mappedCommentsAndUsers = new HashMap<>();
        if (postExists){
            List<Object[]> commentsAndUsers = commentRepository.findCommentsAndUsersByPostId(postId);
            for (Object[] result : commentsAndUsers){
              Comment comment = (Comment) result[0];
              User user = (User) result[1];
              CommentResponseDto commentResponseDto = commentMapper.mapTo(comment);
              UserResponseDto userResponseDto = userMapper.mapTo(user);
              mappedCommentsAndUsers.put(commentResponseDto,userResponseDto);
            }
            return mappedCommentsAndUsers;

        }else{
            throw new PostNotFoundException("post cannot be found", HttpStatus.NOT_FOUND);
        }

    }
}
