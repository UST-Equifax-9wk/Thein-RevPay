package com.revature.revpay.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.revpay.entities.Admin;
import com.revature.revpay.entities.Credential;
import com.revature.revpay.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    AdminController sut;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AdminService adminService;

    @Test
    public void test_SignIn() throws Exception {
        Credential credential = new Credential();
        credential.setUsername("test");
        credential.setPassword("test");
        Admin admin = new Admin();
        admin.setUsername("test");

        when(adminService.signIn(credential)).thenReturn(Optional.of(admin));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(credential);
//        mockMvc.perform(post("/admin-sign-in").content(json)).andExpect(status().isOk());
    }
}
