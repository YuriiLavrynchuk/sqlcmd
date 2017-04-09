package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public Delete(Statement statement) {
        try {
            statement.executeUpdate("DELETE FROM users where id = 5");
            System.out.println("Row deleted");
        } catch (SQLException e) {
            System.out.println("DELETE ERROR");
            e.printStackTrace();
        }
        try {
            statement.close();
            System.out.println("Delete statement closed");
        } catch (SQLException e) {
            System.out.println("Delete statement.close() ERROR");
            e.printStackTrace();
        }
    }
}