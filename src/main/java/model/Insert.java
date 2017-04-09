package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public Insert(Statement statement) {
        try {
            statement.executeUpdate("INSERT INTO users VALUES('5', 'user5', '5555')");
            System.out.println("Row added");
        } catch (SQLException e) {
            System.out.println("Insert ERROR");
            e.printStackTrace();
        }
        try {
            statement.close();
            System.out.println("Insert statement closed");
        } catch (SQLException e) {
            System.out.println("Insert statement.close() ERROR");
            e.printStackTrace();
        }
    }
}