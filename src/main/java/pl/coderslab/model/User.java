package src.main.java.pl.s235jr.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        // TODO add lib to hash
        System.out.println("TODO add lib to hash");
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static void loadAllByGrupId(Connection connection, int id) throws SQLException {

        String query = "SELECT * FROM users LEFT JOIN user_group AS us ON users.user_group_ID = us.id WHERE user_group_ID=?;";
        PreparedStatement sql = connection.prepareStatement(query);
        sql.setInt(1, id);
        ResultSet rs = sql.executeQuery();
        while (rs.next()) {

            String username = rs.getString("users.username");
            String email = rs.getString("users.email");
            String userGropuName = rs.getString("us.name");

            System.out.println("Username: " + username + " email: " + email + " groupname: " + userGropuName);
        }
    }


    public static User loadById(Connection connection, int id) throws SQLException {

        PreparedStatement sql = connection.prepareStatement("SELECT username, email, password FROM users WHERE id = ?");
        sql.setInt(1, id);
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {

            User user = new User();
            user.id = id;
            user.username = rs.getString("username");
            user.email = rs.getString("email");
            user.password = rs.getString("password");

            return user;
        } else {
            return null;
        }
    }

    public void saveToDB(Connection connection) throws SQLException {
        if (id == 0) {
            insert(connection);
        } else {
            update(connection);
        }
    }

    private void update(Connection connection) throws SQLException {


        String query = "UPDATE users SET username=?, email=?, password=? WHERE id=?";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1, username);
        sql.setString(2, email);
        sql.setString(3, password);
        sql.setInt(4, id);
        sql.executeUpdate();

    }

    private void insert(Connection connection) throws SQLException {

        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1, username);
        sql.setString(2, email);
        sql.setString(3, password);
        sql.executeUpdate();
        ResultSet rs = sql.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
    }

    public static void loadAll(Connection connection) throws SQLException {

        String query = "SELECT * FROM users;";
        PreparedStatement sql = connection.prepareStatement(query);
        ResultSet rs = sql.executeQuery();

        while (rs.next()) {

            User user = new User();
            user.id = rs.getInt("id");
            user.username = rs.getString("username");
            user.email = rs.getString("email");
            System.out.println(user);

        }
    }

    public static void deleteByID(Connection connection, int id) throws SQLException {

        String query = "DELETE FROM users WHERE id=?;";
        PreparedStatement sql = connection.prepareStatement(query);
        sql.setInt(1, id);
        int executeUpdate = sql.executeUpdate();
        System.out.println("Usunięto " + executeUpdate + " użytkowników.");

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
