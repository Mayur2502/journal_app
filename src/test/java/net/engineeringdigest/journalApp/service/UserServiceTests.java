package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    public void ListfindAllTest(){
        assertNotNull(userRepository.findAll());
        long totalUsers = userService.getTotalUsers();
        System.out.println("Total Users: " + totalUsers);
        assertTrue(totalUsers >= 0);

    }

    @ParameterizedTest
    @ArgumentsSource(FindByID.class)
    public void testFindUserById(ObjectId id) {
        Optional<User> user = userService.findById(id);
        assertTrue(user.isPresent(), "User should be found in the database");
        System.out.println("User found: " + user.get().getUserName());
    }



    @ParameterizedTest
   @ArgumentsSource(FindByUsername.class)
    public void findByUsername(String user){
        assertNotNull(userService.findByUserName(user));
    }




    @ParameterizedTest
   @ArgumentsSource(SaveNewUser.class)
    public void SaveNewTest(User user){
        assertTrue(userService.saveNewUser(user));
    }

}
