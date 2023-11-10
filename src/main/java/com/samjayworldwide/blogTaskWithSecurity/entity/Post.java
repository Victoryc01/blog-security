package com.samjayworldwide.blogTaskWithSecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_table")
public class Post {
    @Id
    @SequenceGenerator(name = "post_sequence"
            ,sequenceName = "post_sequence"
            ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post_sequence")
    @Column(name = "id")
    private Long id;
    private String content;
    private String category;
    @CreationTimestamp
    private LocalDateTime datePosted;


    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL
    )
    private List<Comment> comments = new ArrayList<>();


    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_post_fk"
            )
    )
    private User user;


    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL
    )
    private List<Likes> likes = new ArrayList<>();


    public void addCommentToPost (Comment comment){
        if (comments == null){
            comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setPost(this);
    }


    public void  addLikeToPost(Likes like){
        if (likes == null){
            likes = new ArrayList<>();
        }
        likes.add(like);
        like.setPost(this);
    }


}
