package controller;

import model.DataSet;
import model.Select;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class ExSelect {
    private Connection connection;
    private String select;

    public ExSelect(Connection connection, String select){
        this.connection = connection;
        this.select = select;
    }

    public ExSelect select(){
        DataSet[] select = new DataSet[0];
        try {
            select = new Select(connection.createStatement()).select(this.select);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        System.out.println(Arrays.toString(select));
        return null;
    }
}
