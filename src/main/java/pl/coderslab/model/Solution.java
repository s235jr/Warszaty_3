package src.main.java.pl.s235jr.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int users_id;

    public Solution() {
    }

    public Solution(String created, String updated, String description, int exercise_id, int users_id) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getDescription() {
        return description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public static void loadAllByExerciseId(Connection connection, int id) throws SQLException {

        PreparedStatement sql = connection.prepareStatement("SELECT * FROM solution WHERE exercise_id=? ORDER BY created;");
        sql.setInt(1, id);

        ResultSet rs = sql.executeQuery();
        while (rs.next()) {

            Solution solution = new Solution();
            solution.id = rs.getInt("id");
            solution.created = rs.getString("created");
            solution.updated = rs.getString("updated");
            solution.description = rs.getString("description");
            solution.exercise_id = rs.getInt("exercise_id");
            solution.users_id = rs.getInt("user_id");
            System.out.println(solution);

        }
    }

    public static void loadAllByUserId(Connection connection, int id) throws SQLException {

        PreparedStatement sql = connection.prepareStatement("SELECT * FROM solution WHERE user_id=?;");
        sql.setInt(1, id);

        ResultSet rs = sql.executeQuery();
        while (rs.next()) {

            Solution solution = new Solution();
            solution.id = rs.getInt("id");
            solution.created = rs.getString("created");
            solution.updated = rs.getString("updated");
            solution.description = rs.getString("description");
            solution.exercise_id = rs.getInt("exercise_id");
            solution.users_id = rs.getInt("user_id");
            System.out.println(solution);

        }

    }

    public static Solution loadById(Connection connection, int id) throws SQLException {

        PreparedStatement sql = connection.prepareStatement("SELECT *  FROM solution WHERE id = ?");
        sql.setInt(1, id);
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {

            Solution solution = new Solution();
            solution.id = id;
            solution.created = rs.getString("created");
            solution.updated = rs.getString("updated");
            solution.description = rs.getString("description");
            solution.exercise_id = rs.getInt("exercise_id");
            solution.users_id = rs.getInt("user_id");
            return solution;
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

        String query = "UPDATE solution SET created=?, updated=?, description=?, exercise_id=?, user_id=? WHERE id=?";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1, created);
        sql.setString(2, updated);
        sql.setString(3, description);
        sql.setInt(4, exercise_id);
        sql.setInt(5, users_id);
        sql.setInt(6, id);
        sql.executeUpdate();

    }

    private void insert(Connection connection) throws SQLException {

        String query = "INSERT INTO solution (created, updated, description, exercise_id, user_id) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1, created);
        sql.setString(2, updated);
        sql.setString(3, description);
        sql.setInt(4, exercise_id);
        sql.setInt(5, users_id);
        sql.executeUpdate();
        ResultSet rs = sql.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
    }

    public static void loadAll(Connection connection) throws SQLException {

        String query = "SELECT * FROM solution;";
        PreparedStatement sql = connection.prepareStatement(query);
        ResultSet rs = sql.executeQuery();

        while (rs.next()) {
            Solution solution = new Solution();
            solution.id = rs.getInt("id");
            solution.created = rs.getString("created");
            solution.updated = rs.getString("updated");
            solution.description = rs.getString("description");
            solution.exercise_id = rs.getInt("exercise_id");
            solution.users_id = rs.getInt("user_id");
            System.out.println(solution);

        }
    }

    public static void deleteByID(Connection connection, int id) throws SQLException {

        String query = "DELETE FROM solution WHERE id=?;";
        PreparedStatement sql = connection.prepareStatement(query);
        sql.setInt(1, id);
        int executeUpdate = sql.executeUpdate();
        System.out.println("Usunięto " + executeUpdate + " rozwiązanie.");
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", users_id=" + users_id +
                '}';
    }
}
