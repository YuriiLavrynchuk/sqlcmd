package controller;

import model.Insert;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 08.04.2017.
 */
public class ExInsert{
    private Connection connection;
    private String insertmsg;

    public ExInsert(Connection connection, String insertmsg) {
        this.connection = connection;
        this.insertmsg = insertmsg;
    }

    public ExInsert insert(){
        try {
            Insert insert = new Insert(connection.createStatement(), insertmsg);
        } catch (SQLException e) {
            System.out.println("ExInsert insert ERROR");
            e.printStackTrace();
        }
        return null;
    }
}
