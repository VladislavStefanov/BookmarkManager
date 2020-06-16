package bg.sofia.uni.fmi.mjt.bookmarks.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    private static final String PASSWORD = "1234";

    private static final String USERNAME = "vladi";

    @Mock
    private Map<String, String> passwordsByUsernames;

    @InjectMocks
    private UserRepository userRepository;

    @Test
    public void testRegister() {
        Mockito.when(passwordsByUsernames.containsKey(USERNAME))
                .thenReturn(false);

        Mockito.when(passwordsByUsernames.get(USERNAME)).thenReturn(PASSWORD);

        assertTrue(userRepository.register(USERNAME, PASSWORD));
        assertTrue(userRepository.isRegistered(USERNAME, PASSWORD));
    }

    @Test
    public void testRegisterTakenUsername() {
        Mockito.when(passwordsByUsernames.containsKey(USERNAME))
                .thenReturn(true);

        assertFalse(userRepository.register(USERNAME, PASSWORD));
    }

}
