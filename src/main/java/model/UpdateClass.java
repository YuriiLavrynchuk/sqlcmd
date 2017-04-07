package model;

import java.sql.SQLException;
import java.sql.Statement;

public class UpdateClass {
    public UpdateClass(Statement statement){
        try {
            statement.executeUpdate("UPDATE users SET password = 'aaaa' where id = '3'");
            System.out.println("Row updated");
        } catch (SQLException e) {
            System.out.println("UPDATE ERROR");
            e.printStackTrace();
        }
    }
}