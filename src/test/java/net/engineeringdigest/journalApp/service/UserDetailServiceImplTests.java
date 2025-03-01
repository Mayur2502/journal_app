package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;

public class UserDetailServiceImplTests {


    @Autowired
    private UserDetailServiceImpl userDetailService;
 
        @Mock
        private UserRepository userRepository;

        void loadUserByUsernameTests(){
            when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("sdfsj").build());
            userDetailService.loadUserByUsername("ram");
        }
}
