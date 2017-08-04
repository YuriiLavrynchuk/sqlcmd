package model;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertUpdateDeleteCreate {
    public void run(Statement statement, String updateMsg) throws SQLException {
        try {
            statement.executeUpdate(updateMsg);
        } catch (SQLException e){
            throw e;
        }
    }
}
