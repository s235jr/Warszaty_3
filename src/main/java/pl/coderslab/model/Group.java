package src.main.java.pl.s235jr.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Group {

    private int id;
    private String name;

    public Group(String name) {
        this.id = id;
        this.name = name;
    }

    public Group() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Group loadById(Connection connection, int id) throws SQLException {

        PreparedStatement sql = connection.prepareStatement("SELECT name FROM user_group WHERE id = ?");
        sql.setInt(1, id);
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {

            Group group = new Group();
            group.id = id;
            group.name = rs.getString("name");

            return group;
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

        String query = "UPDATE user_group SET name=? WHERE id=?";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1, name);
        sql.setInt(2, id);
        sql.executeUpdate();

    }

    private void insert(Connection connection) throws SQLException {

        String query = "INSERT INTO user_group (name) VALUES (?);";
        PreparedStatement sql = connection.prepareStatement(query, new String[]{"id"});
        sql.setString(1, name);
        sql.executeUpdate();
        ResultSet rs = sql.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
    }

    public static void loadAll(Connection connection) throws SQLException {

        String query = "SELECT * FROM user_group;";
        PreparedStatement sql = connection.prepareStatement(query);
        ResultSet rs = sql.executeQuery();

        while (rs.next()) {
            Group group = new Group();
            group.id = rs.getInt("id");
            group.name = rs.getString("name");
            System.out.println(group);

        }
    }

    public static void deleteByID(Connection connection, int id) throws SQLException {

        String query = "DELETE FROM user_group WHERE id=?;";
        PreparedStatement sql = connection.prepareStatement(query);
        sql.setInt(1, id);
        int executeUpdate = sql.executeUpdate();
        System.out.println("Usunięto " + executeUpdate + " grupę.");
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
