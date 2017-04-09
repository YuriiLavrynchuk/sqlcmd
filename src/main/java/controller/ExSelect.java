package controller;

import exeption.InvalidException;
import model.Select;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 08.04.2017.
 */
public class ExSelect extends ExConnectToDB {
    private Connection connection;

    public ExSelect(String db, String user, String pass) {
        super(db, user, pass);
        try {
            connection = super.execute();
        } catch (InvalidException e) {
            System.out.println("ExSelect create connection ERROR");
            e.printStackTrace();
        }
    }

    public ExSelect select(){
        try {
            Select select = new Select(connection.createStatement());
        } catch (SQLException e) {
            System.out.println("ExSelect select ERROR");
            e.printStackTrace();
        }
        try {
            connection.close();
            System.out.println("ExSelect connection closed");
        } catch (SQLException e) {
            System.out.println("ExSelect connection.close() ERROR");
            e.printStackTrace();
        }
        return null;
    }
}
