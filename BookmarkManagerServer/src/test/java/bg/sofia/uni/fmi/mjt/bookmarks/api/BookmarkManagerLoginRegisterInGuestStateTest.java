package bg.sofia.uni.fmi.mjt.bookmarks.api;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import bg.sofia.uni.fmi.mjt.bookmarks.Response;
import bg.sofia.uni.fmi.mjt.bookmarks.api.state.GuestBookmarkManagerState;
import bg.sofia.uni.fmi.mjt.bookmarks.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkManagerLoginRegisterInGuestStateTest {

    private static final String PASSWORD = "1234";

    private static final String USERNAME = "vladi";

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookmarkManager bookmarkManagerWrapper;

    private GuestBookmarkManagerState bookmarkManagerState = new GuestBookmarkManagerState(
            null);

    @Before
    public void setUp() {
        try {
            Field userRepositoryField = bookmarkManagerState.getClass()
                    .getSuperclass().getDeclaredField("userRepository");
            userRepositoryField.setAccessible(true);
            userRepositoryField.set(bookmarkManagerState, userRepository);
            userRepositoryField.setAccessible(false);

            Field bookmarkManagerWrapperField = bookmarkManagerState.getClass()
                    .getSuperclass().getDeclaredField("bookmarkManagerWrapper");
            bookmarkManagerWrapperField.setAccessible(true);
            bookmarkManagerWrapperField.set(bookmarkManagerState,
                    bookmarkManagerWrapper);
            bookmarkManagerWrapperField.setAccessible(false);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Test
    public void testRegister() {

        Mockito.when(userRepository.register(USERNAME, PASSWORD))
                .thenReturn(true);

        assertEquals(Response.REGISTERED.getMessage(),
                bookmarkManagerState.register(USERNAME, PASSWORD));
    }

    @Test
    public void testRegisterAlreadyExists() {
        Mockito.when(userRepository.register(USERNAME, PASSWORD))
                .thenReturn(false);

        assertEquals(Response.USER_WITH_USERNAME_ALREADY_EXISTS.getMessage(),
                bookmarkManagerState.register(USERNAME, PASSWORD));
    }

    @Test
    public void testLogin() {
        Mockito.when(userRepository.isRegistered(USERNAME, PASSWORD))
                .thenReturn(true);

        assertEquals(Response.LOGGED_IN.getMessage(),
                bookmarkManagerState.login(USERNAME, PASSWORD));

        Mockito.verify(bookmarkManagerWrapper).setState(Mockito.any());
    }

    @Test
    public void testLoginInvalidCredentials() {
        Mockito.when(userRepository.isRegistered(USERNAME, PASSWORD))
                .thenReturn(false);

        assertEquals(Response.INVALID_CREDENTIALS.getMessage(),
                bookmarkManagerState.login(USERNAME, PASSWORD));
    }
}
