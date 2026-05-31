package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@Disabled
//@SpringBootTest
@ActiveProfiles("dev")
public class UserDetailsServiceImplementationTest {

//    @Autowired
    @InjectMocks
    private UserDetailsServiceImplementation userDetailsServiceImplementation;

//    @MockBean
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        // whenever this method is called, then return the mock entries as it is
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).
             thenReturn(User.builder().username("ravi").password("encrypted").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImplementation.loadUserByUsername("ravi");
        Assertions.assertNotNull(user);
    }
}
