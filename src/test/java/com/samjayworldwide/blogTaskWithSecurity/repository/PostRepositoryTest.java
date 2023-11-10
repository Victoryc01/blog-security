package com.samjayworldwide.blogTaskWithSecurity.repository;

import com.samjayworldwide.blogTaskWithSecurity.entity.Post;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testIfPostRepositoryCanSaveAPostToTheDataBase(){
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
      Post savedPost =  postRepository.save(post);
        Assertions.assertThat(savedPost).isNotNull();
        Assertions.assertThat(savedPost.getId()).isGreaterThan(0);

    }

    @Test
    public void testIfPostRepositoryCanFindAPostByAGivenCategory(){
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
        List<Post>  foundPost = postRepository.findPostsByCategory("african");
        Assertions.assertThat(foundPost).isNotNull();
        Assertions.assertThat(foundPost.size()).isGreaterThan(0);
    }
    @Test
    public void testIfPostRepositoryCanDeleteAPostByAGivenId(){
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
        assertAll(()->postRepository.deleteById(1L));
    }
}
