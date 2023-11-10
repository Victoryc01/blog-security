package com.samjayworldwide.blogTaskWithSecurity.service;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.PostRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.PostResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Post;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import com.samjayworldwide.blogTaskWithSecurity.repository.PostRepository;
import com.samjayworldwide.blogTaskWithSecurity.repository.UserRepository;
import com.samjayworldwide.blogTaskWithSecurity.serviceImplementation.PostServiceImplementation;
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
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private Mapper<Post,PostResponseDto> postMapper;
    @InjectMocks
    private PostServiceImplementation postService;

    @Test
    public void  testIfPostServiceCanSaveAPostToTheDataBase(){
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

        PostResponseDto postResponseDto = PostResponseDto
                .builder()
                .content("African style")
                .category("african")
                .datePosted(LocalDateTime.now())
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
        when(postMapper.mapTo(post)).thenReturn(postResponseDto);

        PostRequestDto postRequestDto = PostRequestDto
                .builder()
                .content("African style")
                .category("african")
                .build();

        PostResponseDto savedPost = postService.writeAPost(postRequestDto,1L);
        Assertions.assertThat(savedPost).isNotNull();
    }

    @Test
    public void testIfPostServiceFindsAPostByAGivenCategory(){
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

        PostResponseDto postResponseDto = PostResponseDto
                .builder()
                .content("African style")
                .category("african")
                .datePosted(LocalDateTime.now())
                .build();



        when(postRepository.findPostsByCategory("african")).thenReturn(List.of(post));
        when(postMapper.mapTo(post)).thenReturn(postResponseDto);

        List<PostResponseDto> foundPost = postService.findAPost("african");
        Assertions.assertThat(foundPost).isNotNull();
        Assertions.assertThat(foundPost.size()).isGreaterThan(0);

    }

    @Test
    public void testThatPostServiceDeletesAPostByAGivenId(){
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
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        assertAll(()-> postService.deleteAPost(1L,1L));
    }


}
