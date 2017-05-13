package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public Update(Statement statement, String updatemsg){
        try {
            statement.executeUpdate(updatemsg);
            System.out.println("Row updated");
        } catch (SQLException e) {
            System.out.println("Update ERROR");
            e.printStackTrace();
        }
    }
}