package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public Delete (Statement statement, String deleteMsg) throws SQLException {
        try {
            statement.executeUpdate(deleteMsg);
        } catch (SQLException e){
            throw e;
        }
    }
}