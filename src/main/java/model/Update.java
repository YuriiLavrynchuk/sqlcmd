package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public Update(Statement statement){
        try {
            statement.executeUpdate("UPDATE users SET password = 'aaaa' where id = '3'");
            System.out.println("Row updated");
        } catch (SQLException e) {
            System.out.println("Update ERROR");
            e.printStackTrace();
        }
        try {
            statement.close();
            System.out.println("Update statement closed");
        } catch (SQLException e) {
            System.out.println("Update statement.close() ERROR");
            e.printStackTrace();
        }
    }
}