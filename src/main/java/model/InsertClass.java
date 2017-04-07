package model;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertClass {
    public InsertClass(Statement statement) {
        try {
            statement.executeUpdate("INSERT INTO users VALUES('5', 'user5', '5555')");
            System.out.println("Row added");
        } catch (SQLException e) {
            System.out.println("INSERT ERROR");
            e.printStackTrace();
        }
    }
}