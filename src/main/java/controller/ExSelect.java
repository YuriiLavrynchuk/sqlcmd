package controller;

import exeption.InvalidException;
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

    public ExSelect(Connection connection, String select){
        this.connection = connection;
        this.select = select;
    }

    public ExSelect select(){
        DataSet[] sel = new DataSet[0];
        try {
            sel = new Select(connection.createStatement()).select(select);
        } catch (Exception e) {
//            connection.close();
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(sel));
        return null;
    }
}
