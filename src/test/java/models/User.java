package models;

public class User {
    private String username;
    private String password;
    private boolean isLoggedIn;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    public void login() {
        if ("testUser".equals(username) && "testPassword".equals(password)) {
            isLoggedIn = true;
        }
    }

    public boolean isAuthenticated() {
        return isLoggedIn;
    }

    public String getUsername() {
        return username;
    }

    // Метод для получения длины имени пользователя
    public int getUsernameLength() {
        return username.length();
    }

    // Метод для сброса пароля
    public void resetPassword(String newPassword) {
        this.password = newPassword;
        this.isLoggedIn = false;
    }
}
