package com.samjayworldwide.blogTaskWithSecurity.repository;

import com.samjayworldwide.blogTaskWithSecurity.entity.Comment;
import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPostId(Long postId);

    @Query("SELECT c, u FROM Comment c JOIN c.user u WHERE c.post.id = :postId")
    List<Object[]> findCommentsAndUsersByPostId(Long postId);
}
