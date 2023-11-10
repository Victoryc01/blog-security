package com.samjayworldwide.blogTaskWithSecurity.serviceImplementation;

import com.samjayworldwide.blogTaskWithSecurity.dto.request.LikeRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.response.LikeResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Likes;
import com.samjayworldwide.blogTaskWithSecurity.entity.Post;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import com.samjayworldwide.blogTaskWithSecurity.exception.InvalidNumberOfLikeException;
import com.samjayworldwide.blogTaskWithSecurity.exception.PostNotFoundException;
import com.samjayworldwide.blogTaskWithSecurity.exception.UserNotFoundException;
import com.samjayworldwide.blogTaskWithSecurity.mappers.Mapper;
import com.samjayworldwide.blogTaskWithSecurity.repository.LikeRepository;
import com.samjayworldwide.blogTaskWithSecurity.repository.PostRepository;
import com.samjayworldwide.blogTaskWithSecurity.repository.UserRepository;
import com.samjayworldwide.blogTaskWithSecurity.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImplementation implements LikeService {
    private PostRepository postRepository;
    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private Mapper<Likes,LikeResponseDto> likeMapper;
       @Autowired
    public LikeServiceImplementation(PostRepository postRepository,
                                     UserRepository userRepository,
                                     LikeRepository likeRepository,
                                     Mapper<Likes,LikeResponseDto> likeMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }

    @Override
    public LikeResponseDto addALikeToAPost(LikeRequestDto likeRequestDto, Long postId, Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
            throw new UserNotFoundException("user with " + userId +" not found", HttpStatus.NOT_FOUND);
        }
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            throw new PostNotFoundException("post cannot be found",HttpStatus.NOT_FOUND);
        }
        if (likeRequestDto.getLike() > 1){
            throw new InvalidNumberOfLikeException("user cannot like a post more than once",HttpStatus.BAD_REQUEST);
        }
        Likes likes = Likes
                .builder()
                .like(likeRequestDto.getLike())
                .build();
        user.get().addLikes(likes);
        post.get().addLikeToPost(likes);
        Likes savedLike = likeRepository.save(likes);
        return likeMapper.mapTo(savedLike);

    }
}
