package tests;

import models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testLoginSuccess() {
        long startTime = System.nanoTime();

        User user = new User("testUser", "testPassword");
        user.login();
        assertTrue(user.isAuthenticated());

        long endTime = System.nanoTime();
        System.out.println("Время выполнения testLoginSuccess: " + (endTime - startTime) / 1_000_000 + " мс");
    }

    @Test
    public void testLoginFailure() {
        long startTime = System.nanoTime();

        User user = new User("wrongUser", "wrongPassword");
        user.login();
        assertFalse(user.isAuthenticated());

        long endTime = System.nanoTime();
        System.out.println("Время выполнения testLoginFailure: " + (endTime - startTime) / 1_000_000 + " мс");
    }

    @Test
    public void testUsernameLength() {
        long startTime = System.nanoTime();

        User user = new User("testUser", "testPassword");
        int length = user.getUsernameLength();
        assertEquals(8, length);

        long endTime = System.nanoTime();
        System.out.println("Время выполнения testUsernameLength: " + (endTime - startTime) / 1_000_000 + " мс");
    }

    @Test
    public void testResetPassword() {
        long startTime = System.nanoTime();

        User user = new User("testUser", "testPassword");
        user.login();
        user.resetPassword("newPassword123");
        assertFalse(user.isAuthenticated());

        long endTime = System.nanoTime();
        System.out.println("Время выполнения testResetPassword: " + (endTime - startTime) / 1_000_000 + " мс");
    }
}
