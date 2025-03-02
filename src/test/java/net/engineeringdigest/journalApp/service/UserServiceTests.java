package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setId(new ObjectId());
        testUser.setUserName("testuser");
        testUser.setPassword("password");
    }

    @Test
    public void testGetTotalUsers() {
        when(userRepository.count()).thenReturn(5L);
        assertEquals(5L, userService.getTotalUsers());
        verify(userRepository, times(1)).count();
    }

    @Test
    public void testSaveNewUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        boolean result = userService.saveNewUser(testUser);

        assertTrue(result, "User should be saved successfully");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals("testuser", savedUser.getUserName());
        assertNotEquals("password", savedUser.getPassword(), "Password should be encoded");
    }

    @Test
    public void testSaveNewUserWithException() {
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Database error"));
        boolean result = userService.saveNewUser(testUser);
        assertFalse(result, "User saving should fail due to exception");
    }

    @Test
    public void testSaveAdmin() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        userService.saveAdmin(testUser);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals(Arrays.asList("ADMIN", "USER"), savedUser.getRoles());
    }

    @Test
    public void testFindUserById() {
        ObjectId userId = testUser.getId();
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        Optional<User> foundUser = userService.findById(userId);
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUserName());
    }

    @Test
    public void testFindUserByIdNotFound() {
        ObjectId invalidId = new ObjectId();
        when(userRepository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findById(invalidId);
        assertFalse(foundUser.isPresent(), "User should not be found");
    }

    @Test
    public void testFindByUserName() {
        when(userRepository.findByUserName("testuser")).thenReturn(testUser);
        User foundUser = userService.findByUserName("testuser");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUserName());
    }

    @Test
    public void testFindByUserNameNotFound() {
        when(userRepository.findByUserName("unknown")).thenReturn(null);
        User foundUser = userService.findByUserName("unknown");
        assertNull(foundUser, "User should not be found");
    }

    @Test
    public void testDeleteById() {
        ObjectId userId = testUser.getId();
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
