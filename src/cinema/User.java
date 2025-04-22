package cinema;

/**
 * Represents a user with a login, password, and admin status.
 */
public class User {
    private String login;
    private String password;
    private boolean isAdmin;

    public User(String login, String password, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean authenticate(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}