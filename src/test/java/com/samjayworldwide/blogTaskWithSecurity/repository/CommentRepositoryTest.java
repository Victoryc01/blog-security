package com.samjayworldwide.blogTaskWithSecurity.repository;

import com.samjayworldwide.blogTaskWithSecurity.entity.Comment;
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
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testIfCommentRepositoryAddsANewCommentToTheDatabase(){
        Comment comment = Comment
                .builder()
                .message("this is a lovely post")
                .build();

        Comment savedComment = commentRepository.save(comment);
        Assertions.assertThat(savedComment).isNotNull();
        Assertions.assertThat(savedComment.getId()).isGreaterThan(0);
    }

    @Test
    public void testIfCommentRepositoryFindsACommentByPostsId(){

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
                .message("this is a lovely post")
                .user(user)
                .post(post)
                .build();
       Comment savedComment = commentRepository.save(comment);
        List<Comment> comments = commentRepository.findCommentsByPostId(post.getId());
        Assertions.assertThat(savedComment).isNotNull();
        Assertions.assertThat(comments.size()).isGreaterThan(0);
    }

    @Test
    public void testIfCommentRepositoryCanDeleteACommentByAGivenId(){
        User user = User
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();
        userRepository.save(user);
        Comment comment = Comment
                .builder()
                .message("this is a lovely post")
                .user(user)
                .build();
        commentRepository.save(comment);

        assertAll(()->commentRepository.deleteById(1L));
    }
}
