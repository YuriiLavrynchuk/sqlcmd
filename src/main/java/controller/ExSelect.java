package controller;

import model.DataSet;
import model.Select;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Admin on 08.04.2017.
 */
public class ExSelect extends ExConnectToDB {
    private Connection connection;

    public ExSelect(String db, String user, String pass) {
        super(db, user, pass);
        connection = super.getConnect();
    }

    public ExSelect select(){
        try {
            DataSet[] sel = new Select(connection.createStatement()).select("users");
            System.out.println(Arrays.toString(sel));

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
