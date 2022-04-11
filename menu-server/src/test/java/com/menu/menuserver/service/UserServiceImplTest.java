package com.menu.menuserver.service;

import com.menu.menuserver.model.User;
import com.menu.menuserver.model.Role;
import com.menu.menuserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
/*
    private static final User user1 = new User(1L, "Joe", "12345", LocalDateTime.now(), Role.USER);
    private static final User user2 = new User(2L, "John", "12345", LocalDateTime.now(), Role.USER);
    private static final List<User> users = List.of(user1, user2);

    @Test
    void testFindByUsername() {
        given(userRepository.findByUsername(user1.getUsername())).willReturn(Optional.of(user1));
        Optional<User> actual = service.findByUsername(user1.getUsername());
        assertTrue(actual.isPresent() && user1.equals(actual.get()));
    }
*/
}