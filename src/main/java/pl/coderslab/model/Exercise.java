package src.main.java.pl.s235jr.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exercise {

    private int id;
    private String title;
    private String description;

    public Exercise() {
    }

    public Exercise(String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static Exercise loadById(Connection connection, int id) throws SQLException {

        PreparedStatement sql = connection.prepareStatement("SELECT id, title, description  FROM exercise WHERE id = ?");
        sql.setInt(1, id);
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {

            Exercise exercise = new Exercise();
            exercise.id = id;
            exercise.title = rs.getString("title");
            exercise.description = rs.getString("description");

            return exercise;
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

        String query = "UPDATE exercise SET title=?, description=? WHERE id=?";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1, title);
        sql.setString(2, description);
        sql.setInt(3, id);
        sql.executeUpdate();

    }

    private void insert(Connection connection) throws SQLException {

        String query = "INSERT INTO exercise (title, description) VALUES (?, ?);";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1, title);
        sql.setString(2, description);
        sql.executeUpdate();
        ResultSet rs = sql.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
    }

    public static void loadAll(Connection connection) throws SQLException {

        String query = "SELECT * FROM exercise;";
        PreparedStatement sql = connection.prepareStatement(query);
        ResultSet rs = sql.executeQuery();

        while (rs.next()) {
            Exercise exercise = new Exercise();
            exercise.id = rs.getInt("id");
            exercise.title = rs.getString("title");
            exercise.description = rs.getString("description");
            System.out.println(exercise);
        }
    }

    public static void deleteByID(Connection connection, int id) throws SQLException {

        String query = "DELETE FROM exercise WHERE id=?;";
        PreparedStatement sql = connection.prepareStatement(query);
        sql.setInt(1, id);
        int executeUpdate = sql.executeUpdate();
        System.out.println("Usunięto " + executeUpdate + " ćwiczenie.");
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
