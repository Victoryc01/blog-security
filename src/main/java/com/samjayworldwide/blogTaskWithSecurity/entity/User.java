package com.samjayworldwide.blogTaskWithSecurity.entity;

import com.samjayworldwide.blogTaskWithSecurity.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    @Column(name = "id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String password;
    @Transient
    private String retypePassword;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Comment> comments = new ArrayList<>();


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Post> posts = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Likes> likes = new ArrayList<>();




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addComments (Comment comment){
        if(comments == null){
          comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setUser(this);
    }

    public void addLikes (Likes like){
        if (likes == null){
            likes = new ArrayList<>();
        }
        likes.add(like);
        like.setUser(this);
    }

    public void  removeComments(Comment comment){
        if (comments == null){
            comments = new ArrayList<>();
        }
        comments.remove(comment);
        comment.setUser(this);
    }
    public void addPosts(Post post){
        if (posts == null){
            posts = new ArrayList<>();
        }
        posts.add(post);
        post.setUser(this);
    }
    public void removePosts(Post post){
        if (posts == null){
            posts = new ArrayList<>();
        }
        posts.remove(post);
        post.setUser(this);
    }

}
