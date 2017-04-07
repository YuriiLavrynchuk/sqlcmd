package model;

import java.sql.SQLException;
import java.sql.Statement;

public class DeleteClass {
    public DeleteClass(Statement statement) throws SQLException {
        statement.executeUpdate("DELETE FROM users where id = 5");
        System.out.println("Row deleted");
//        statement.close();
    }
}