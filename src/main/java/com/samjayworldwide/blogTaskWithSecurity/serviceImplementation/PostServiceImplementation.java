package com.samjayworldwide.blogTaskWithSecurity.serviceImplementation;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.PostRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.PostResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Post;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import com.samjayworldwide.blogTaskWithSecurity.exception.PostNotFoundException;
import com.samjayworldwide.blogTaskWithSecurity.exception.UserNotFoundException;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import com.samjayworldwide.blogTaskWithSecurity.repository.PostRepository;
import com.samjayworldwide.blogTaskWithSecurity.repository.UserRepository;
import com.samjayworldwide.blogTaskWithSecurity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private Mapper<Post,PostResponseDto> postMapper;
       @Autowired
    public PostServiceImplementation(PostRepository postRepository,
                                     UserRepository userRepository,
                                     Mapper<Post,PostResponseDto> postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostResponseDto writeAPost(PostRequestDto postRequestDto, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw new UserNotFoundException("user with " + userId +" not found", HttpStatus.NOT_FOUND);
        }
        Post post = Post
                .builder()
                .content(postRequestDto.getContent())
                .category(postRequestDto.getCategory())
                .build();
        user.get().addPosts(post);
        Post savedPost =  postRepository.save(post);
               return postMapper.mapTo(savedPost);

    }

    @Override
    public List<PostResponseDto> findAPost(String postCategory) {
        List<Post> searchResults = postRepository.findPostsByCategory(postCategory);
        return searchResults.stream().map(postMapper::mapTo).toList();
    }

    @Override
    public void deleteAPost(Long userId, Long postId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw new UserNotFoundException("user with " + userId +" not found", HttpStatus.NOT_FOUND);
        }
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            throw new PostNotFoundException("post with " + postId + " not found",HttpStatus.NOT_FOUND);
        }

        user.get().removePosts(post.get());
        postRepository.deleteById(postId);

    }
}
