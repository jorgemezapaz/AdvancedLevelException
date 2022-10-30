package com.example.exceptionadvanze;

import com.example.exceptionadvanze.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String BASE_URL = "/api/users";

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void saveUserOk() throws Exception {
        var request = new User(null, "Jorge", "M", "j@mail.com");

        mvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveUserErrorEmptyEmail() throws Exception {
        var request = new User(null, "Jorge", "M", "");

        mvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("P-401")))
                .andExpect(jsonPath("$.message", is("Email is required")));
    }

    @Test
    public void saveUserErrorEmptyName() throws Exception {
        var request = new User(null, "", "M", "j@email.com");

        mvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("P-402")))
                .andExpect(jsonPath("$.message", is("User name is required")));
    }

    @Test
    public void saveUserErrorAlreadyExistEmail() throws Exception {
        var request = new User(null, "Jorge", "M", "jorge@gmail.com");

        mvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is("P-300")))
                .andExpect(jsonPath("$.message", is("Email already exist.")));
    }
}
