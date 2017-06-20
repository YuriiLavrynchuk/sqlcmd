package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public Update(Statement statement, String updatemsg) throws SQLException {
        try {
            statement.executeUpdate(updatemsg);
        } catch (SQLException e) {
            System.out.println("Update ERROR");
        } finally {
            statement.close();
        }
    }
}