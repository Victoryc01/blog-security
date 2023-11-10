package com.samjayworldwide.blogTaskWithSecurity.repository;

import com.samjayworldwide.blogTaskWithSecurity.dto.response.PostResponseDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostsByCategory(String category);
}
