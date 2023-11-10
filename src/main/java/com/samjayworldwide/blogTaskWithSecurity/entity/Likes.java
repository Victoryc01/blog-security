package com.samjayworldwide.blogTaskWithSecurity.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "likes_table")
public class Likes {
    @Id
    @SequenceGenerator(name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "like_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "like_count")
    private Long like;


    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_likes_fk"
            )
    )
    private User user;


    @ManyToOne
    @JoinColumn(
            name = "like_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "post_likes_fk"
            )
    )
    private Post post;
}
