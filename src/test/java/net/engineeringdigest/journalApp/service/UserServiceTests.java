package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "saara",
            "arsh",
            "rohit"
    })
    public void testFindByUsername(String name){
//        assertEquals(4, 2+2);
//        assertTrue(5 > 3);

//        User user = userRepository.findByUsername("saara"); // find the user
//        assertNotNull(user);
//        assertTrue(!user.getJournalEntries().isEmpty()); // check if journal entry of user is not empty

        assertNotNull(userRepository.findByUsername(name), "failed for "+name);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "10,2,12",
//            "3,3,9"
//    })
//    public void test(int a, int b, int expected){
//        assertEquals(expected, a+b);
//    }
}
