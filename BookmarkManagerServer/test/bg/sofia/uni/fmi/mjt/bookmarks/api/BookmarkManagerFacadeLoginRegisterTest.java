package bg.sofia.uni.fmi.mjt.bookmarks.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkManagerFacadeLoginRegisterTest {

    private static final String PASSWORD = "1234";

    private static final String USERNAME = "vladi";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookmarkManagerFacade bookmarkManagerFacade;

    @Test
    public void testRegister() {
        Mockito.when(userRepository.register(USERNAME, PASSWORD))
                .thenReturn(true);

        assertEquals(Response.REGISTERED.getMessage(),
                bookmarkManagerFacade.register(USERNAME, PASSWORD));
    }

    @Test
    public void testRegisterAlreadyExists() {
        Mockito.when(userRepository.register(USERNAME, PASSWORD))
                .thenReturn(false);

        assertEquals(Response.USER_WITH_USERNAME_ALREADY_EXISTS.getMessage(),
                bookmarkManagerFacade.register(USERNAME, PASSWORD));
    }

    @Test
    public void testLogin() {
        Mockito.when(userRepository.isRegistered(USERNAME, PASSWORD))
                .thenReturn(true);

        assertEquals(Response.LOGGED_IN.getMessage(),
                bookmarkManagerFacade.login(USERNAME, PASSWORD));
    }

    @Test
    public void testLoginInvalidCredentials() {
        Mockito.when(userRepository.isRegistered(USERNAME, PASSWORD))
                .thenReturn(false);

        assertEquals(Response.INVALID_CREDENTIALS.getMessage(),
                bookmarkManagerFacade.login(USERNAME, PASSWORD));
    }
}
