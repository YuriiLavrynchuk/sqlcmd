package model;

import java.sql.SQLException;
import java.sql.Statement;

public class DeleteClass {
    public DeleteClass(Statement statement) {
        try {
            statement.executeUpdate("DELETE FROM users where id = 5");
            System.out.println("Row deleted");
        } catch (SQLException e) {
            System.out.println("DELETE ERROR");
            e.printStackTrace();
        }
//        statement.close();
    }
}