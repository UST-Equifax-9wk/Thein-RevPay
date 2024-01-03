package com.revature.revpay.controllers;

import com.revature.revpay.entities.User;
import com.revature.revpay.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void test() throws Exception {
        List<User> users = List.of(new User());
        when(userService.findAllByUsernameOrEmail("test", null)).thenReturn(users);

        this.mockMvc.perform(get("/users")
                .param("username", "test")
                .param( "email", ""))
                .andExpect(status().isOk());
    }
}
