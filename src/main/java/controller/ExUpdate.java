package controller;

import model.Update;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 08.04.2017.
 */
public class ExUpdate {
    private Connection connection;
    private String updatemsg;

    public ExUpdate(Connection connection, String updatemsg) {
        this.connection = connection;
        this.updatemsg = updatemsg;
    }

    public ExUpdate update(){
        try {
            Update update = new Update(connection.createStatement(), updatemsg);
        } catch (SQLException e) {
            System.out.println("ExUpdate update ERROR");
            e.printStackTrace();
        }
            return null;
    }
}
