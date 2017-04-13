package controller;

import model.DataSet;
import model.Select;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Admin on 08.04.2017.
 */
public class ExSelect {
    private Connection connection;
    private String select;

    public ExSelect(Connection connection, String select) {
        this.connection = connection;
        this.select = select;
    }

    public ExSelect select(){
        try {
            DataSet[] sel = new Select(connection.createStatement()).select(select);
            System.out.println(Arrays.toString(sel));

        } catch (SQLException e) {
            System.out.println("ExSelect ERROR");
            e.printStackTrace();
        }
        return null;
    }
}
