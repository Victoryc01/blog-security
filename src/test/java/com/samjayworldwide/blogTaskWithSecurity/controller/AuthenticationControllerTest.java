package com.samjayworldwide.blogTaskWithSecurity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samjayworldwide.blogTaskWithSecurity.dto.request.UserRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration.properties")
@ActiveProfiles("controller-tests")
public class AuthenticationControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
     @Autowired
    public AuthenticationControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testIfAuthenticationControllerReturnsHttpStatusOk() throws Exception {
        UserRequestDto userRequestDto = UserRequestDto
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("samuel@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();
        String userJson = objectMapper.writeValueAsString(userRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void  testIfAuthenticationControllerReturnsAnewUserCreated() throws Exception {
        UserRequestDto userRequestDto = UserRequestDto
                .builder()
                .firstName("samuel")
                .lastName("jackson")
                .email("jackson@gmail.com")
                .password("samuelJackson")
                .retypePassword("samuelJackson")
                .build();
        String userJson = objectMapper.writeValueAsString(userRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage").value("congratulations account has been created")

        );

    }
}

