package com.revature.revpay.repositories;

import com.revature.revpay.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository sut;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void test() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setUsername("test");
        user.setEmail("test@email.co");
        User actual = sut.save(user);
        Assertions.assertThat(actual).isEqualTo(entityManager.find(User.class, user.getId()));
    }

    @Test
    public void test_findAllByUsernameOrEmail() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setUsername("test");
        user.setEmail("test@email.co");
        sut.save(user);
        List<User> actual = sut.findAllByUsernameOrEmail("test", "");
        Assertions.assertThat(actual).hasSize(1);
    }

    @Test
    public void test_findByUsername() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setUsername("test");
        user.setEmail("test@email.co");
        sut.save(user);
        Optional<User> actual = sut.findByUsername("test");
        Assertions.assertThat(actual).isEqualTo(Optional.of(user));
    }
}
