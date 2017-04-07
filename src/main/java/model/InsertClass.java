package model;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertClass {
    private InsertClass(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO users VALUES('5', 'user5', '5555')");
        System.out.println("Row added");
    }
}