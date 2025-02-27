package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

@Autowired
     private UserRepository userRepository;
    @ParameterizedTest
    @ValueSource(strings = {
            "mayur",
            "Ram",
            "Vipul"
    })
    public void testfindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name));
//        assertNotNull(userRepository.findByUserName("sham"));


    }

}
