package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public Insert(Statement statement, String insertmsg) throws SQLException {
        try {
            statement.executeUpdate(insertmsg);
            System.out.println("Row inserted");
        } catch (SQLException e) {
            System.out.println("Insert ERROR");
        } finally {
            statement.close();
        }
    }
}