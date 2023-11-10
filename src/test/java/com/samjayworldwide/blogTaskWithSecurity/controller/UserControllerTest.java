package com.samjayworldwide.blogTaskWithSecurity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samjayworldwide.blogTaskWithSecurity.dto.request.CommentRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.request.PostRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.dto.request.UserRequestDto;
import com.samjayworldwide.blogTaskWithSecurity.entity.Comment;
import com.samjayworldwide.blogTaskWithSecurity.service.CommentService;
import com.samjayworldwide.blogTaskWithSecurity.service.PostService;
import com.samjayworldwide.blogTaskWithSecurity.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private UserService userService;
    private PostService postService;
    private CommentService commentService;

     @Autowired
    public UserControllerTest(MockMvc mockMvc,
                              UserService userService,
                              PostService postService,
                              CommentService commentService) {
        this.mockMvc = mockMvc;
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(username = "junior@email.com",password = "samjay2000",roles = "USER")
    public void testThatUserControllerReturnsHttpStatusIsCreated() throws Exception {
        CommentRequestDto commentRequestDto = CommentRequestDto
                .builder()
                .message("LovelyPost")
                .build();
        String commentJson =  objectMapper.writeValueAsString(commentRequestDto);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/users/addAComment/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentJson)

        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    @WithMockUser(username = "junior@email.com",password = "samjay2000",roles = "USER")
    public void testThatUserControllerReturnsACreatedComment() throws Exception {
        CommentRequestDto commentRequestDto = CommentRequestDto
                .builder()
                .message("LovelyPost")
                .build();
        String commentJson =  objectMapper.writeValueAsString(commentRequestDto);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/users/addAComment/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentJson)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("LovelyPost")
        );
    }

    @Test
    @WithMockUser(username = "junior@email.com",password = "samjay2000",roles = "USER")
    public void testThatUserControllerReturnsHttpStatusNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/v1/users/deleteAComment/1/1")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    @WithMockUser(username = "junior@email.com",password = "samjay2000",roles = "USER")
    public void testThatUserControllerReturnsHttpStatusNoContentForAnExistingComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/v1/users/deleteAComment/3/1")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNotFound());

    }


//    @Test
//    @WithMockUser(username = "junior@email.com",password = "samjay2000",roles = "USER")
//    public void testThatUserControllerReturnsHttpStatusOkWhenGettingAllComments() throws Exception {
//
//        CommentRequestDto commentRequestDto = CommentRequestDto
//                .builder()
//                .message("nice post")
//                .build();
//        commentService.addACommentToAPost(commentRequestDto,1L,1L);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/user/viewComments/1")
//                .contentType(MediaType.APPLICATION_JSON)
//
//        ).andExpect(MockMvcResultMatchers.status().isOk());
//    }

}
