package com.samjayworldwide.blogTaskWithSecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment_table")
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_sequence"
            ,sequenceName = "comment_sequence"
            ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comment_sequence")
    @Column(name = "id")
    private Long id;
    private String message;
    @CreationTimestamp
    private LocalDateTime datePosted;

    @ManyToOne
    @JoinColumn(
                    name = "user-id",
                    nullable = false,
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(
                    name = "user_comment_fk"
            )
    )
    private User user;


    @ManyToOne
    @JoinColumn(
            name = "post_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "post_comment_fk"
            )
    )
    private Post post;
}
