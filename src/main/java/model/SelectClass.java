package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectClass {
    public SelectClass(Statement statement) throws SQLException {
        ResultSet select = statement.executeQuery("SELECT * FROM users");
        while (select.next()) {
            System.out.println("Номер в выборке #" + select.getRow()
                    + "\t" + select.getInt("id")
                    + "\t" + select.getString("name")
                    + "\t" + select.getString("password"));
        }
    }
}