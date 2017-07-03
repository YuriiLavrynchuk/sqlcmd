package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public Delete (Statement statement, String deletemsg) throws SQLException {
        try {
            statement.executeUpdate(deletemsg);
            System.out.println("Row deleted");
        } catch (SQLException e) {
            throw e;
        }
    }
}