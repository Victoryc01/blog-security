package com.samjayworldwide.blogTaskWithSecurity.service;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.CommentRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.CommentResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Comment;
import com.samjayworldwide.blogTaskWithSecurity.entity.Post;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import com.samjayworldwide.blogTaskWithSecurity.repository.CommentRepository;
import com.samjayworldwide.blogTaskWithSecurity.repository.PostRepository;
import com.samjayworldwide.blogTaskWithSecurity.repository.UserRepository;
import com.samjayworldwide.blogTaskWithSecurity.serviceImplementation.CommentServiceImplementation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private Mapper<Comment, CommentResponseDto> commentMapper;
    @InjectMocks
    private CommentServiceImplementation commentService;

    @Test
    public void testIfCommentServiceAddsACommentToTheDatabaseAndToAPost(){
        User user = User
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();
        userRepository.save(user);

        Post post = Post
                .builder()
                .user(user)
                .content("African style")
                .category("african")
                .build();
        postRepository.save(post);

        Comment comment = Comment
                .builder()
                .message("lovelyPost")
                .user(user)
                .post(post)
                .build();

        CommentResponseDto commentResponseDto = CommentResponseDto
                .builder()
                .message("lovelyPost")
                .datePosted(LocalDateTime.now())
                .build();



        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);
        when(commentMapper.mapTo(comment)).thenReturn(commentResponseDto);
        CommentRequestDto commentRequestDto = CommentRequestDto
                .builder()
                .message("LovelyPost")
                .build();
        CommentResponseDto savedComment = commentService.addACommentToAPost(commentRequestDto,1L,1L);
        Assertions.assertThat(savedComment).isNotNull();



    }

    @Test
    public void checkIfCommentServiceDeletesACommentByAGivenId(){
        User user = User
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();
        userRepository.save(user);

        Post post = Post
                .builder()
                .user(user)
                .content("African style")
                .category("african")
                .build();
        postRepository.save(post);

        Comment comment = Comment
                .builder()
                .message("lovelyPost")
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);

        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertAll(()-> commentService.deleteAComment(1L,1L));
    }

    @Test
    public void testIfCommentServiceGetsAllCommentsAssociatedWithAPostByAGivenPostId(){
        User user = User
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();
        userRepository.save(user);

        Post post = Post
                .builder()
                .user(user)
                .content("African style")
                .category("african")
                .build();
        postRepository.save(post);

        Comment comment = Comment
                .builder()
                .message("lovelyPost")
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = CommentResponseDto
                .builder()
                .message("lovelyPost")
                .datePosted(LocalDateTime.now())
                .build();

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findCommentsByPostId(1L)).thenReturn(List.of(comment));
        when(commentMapper.mapTo(comment)).thenReturn(commentResponseDto);

        List<CommentResponseDto> comments = commentService.getAllCommentsForAPost(1L);
        Assertions.assertThat(comments).isNotNull();
        Assertions.assertThat(comments.size()).isGreaterThan(0);

    }

}
