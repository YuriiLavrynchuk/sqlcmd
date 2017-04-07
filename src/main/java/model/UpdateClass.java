package model;

import java.sql.SQLException;
import java.sql.Statement;

public class UpdateClass {
    private UpdateClass(Statement statement) throws SQLException {
        statement.executeUpdate("UPDATE users SET password = 'aaaa' where id = '3'");
        System.out.println("Row updated");
    }
}