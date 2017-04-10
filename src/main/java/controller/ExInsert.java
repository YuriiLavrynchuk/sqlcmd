package controller;

import model.Insert;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 08.04.2017.
 */
public class ExInsert extends ExConnectToDB {
    private Connection connection;

    public ExInsert(String db, String user, String pass) {
        super(db, user, pass);
        connection = super.getConnect();
    }

    public ExInsert insert(){
        try {
            Insert ins = new Insert(connection.createStatement());
        } catch (SQLException e) {
            System.out.println("ExInsert insert ERROR");
            e.printStackTrace();
        }
        try {
            connection.close();
            System.out.println("ExInsert connection closed");
        } catch (SQLException e) {
            System.out.println("ExInsert connection.close() ERROR");
            e.printStackTrace();
        }
        return null;
    }
}
