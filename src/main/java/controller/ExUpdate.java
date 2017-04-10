package controller;

import model.Update;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 08.04.2017.
 */
public class ExUpdate extends ExConnectToDB  {
    private Connection connection;

    public ExUpdate(String db, String user, String pass) {
        super(db, user, pass);
        connection = super.getConnect();
    }

    public ExUpdate update(){
        try {
            Update updt = new Update(connection.createStatement());
        } catch (SQLException e) {
            System.out.println("ExUpdate update ERROR");
            e.printStackTrace();
        }
        try {
            connection.close();
            System.out.println("ExUpdate connection closed");
        } catch (SQLException e) {
            System.out.println("ExUpdate connection.close() ERROR");
            e.printStackTrace();
        }
            return null;
    }
}
