package com.revature.revpay.services;

import com.revature.revpay.entities.User;
import com.revature.revpay.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService sut;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void test_saveUserReturnsTheUserSavedByRepo() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setUsername("test");
        user.setEmail("test@email.co");
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName("test");
        savedUser.setLastName("test");
        savedUser.setUsername("test");
        savedUser.setEmail("test@email.co");
        when(userRepository.save(user)).thenReturn(savedUser);
        User actual = sut.saveUser(user);
        Assertions.assertEquals(1L, actual.getId());
    }

    @Test
    public void test_findByIdReturnExistingUser() {
        String id = "1";

        User user = new User();
        user.setId(Long.valueOf(id));
        user.setFirstName("test");
        user.setLastName("test");
        user.setUsername("test");
        user.setEmail("test@email.co");
        when(userRepository.findById(Long.valueOf(id))).thenReturn(Optional.of(user));
        Optional<User> actual = sut.findById(id);
        Assertions.assertEquals(actual, Optional.of(user));
    }

    @Test
    public void test_findAllByUsernameOrEmailReturnExistingUser() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setFirstName("test");
        user.setLastName("test");
        user.setUsername("test");
        user.setEmail("test@email.co");
        users.add(user);
        when(userRepository.findAllByUsernameOrEmail("test", "")).thenReturn(users);
        List<User> actual = sut.findAllByUsernameOrEmail("test", "");

        Assertions.assertEquals(actual, users);
    }

    @Test
    public void test_findByUsername() {

        User user = new User();
        user.setId(1L);
        user.setFirstName("test");
        user.setLastName("test");
        user.setUsername("test");
        user.setEmail("test@email.co");
        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));
        Optional<User> actual = sut.findByUsername("test");
        Assertions.assertEquals(actual, Optional.of(user));
    }
}
