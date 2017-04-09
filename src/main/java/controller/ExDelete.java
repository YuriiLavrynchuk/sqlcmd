package controller;

import exeption.InvalidException;
import model.Delete;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 08.04.2017.
 */
public class ExDelete extends ExConnectToDB {
    private Connection connection;

    public ExDelete(String db, String user, String pass) {
        super(db, user, pass);
        try {
            connection = super.execute();
        } catch (InvalidException e) {
            System.out.println("ExDelete create connection ERROR");
            e.printStackTrace();
        }
    }

    public ExDelete delete(){
        try {
            Delete del = new Delete(connection.createStatement());
        } catch (SQLException e) {
            System.out.println("ExDelete delete ERROR");
            e.printStackTrace();
        }
        try {
            connection.close();
            System.out.println("ExDelete connection closed");
        } catch (SQLException e) {
            System.out.println("ExDelete connection.close() ERROR");
            e.printStackTrace();
        }
            return null;
    }
}

