package controller;

import model.DataSet;
import model.Select;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

class ExSelect {
    private final Connection connection;
    private final String select;

    public ExSelect(Connection connection, String select){
        this.connection = connection;
        this.select = select;
    }

    public void select(){
        DataSet[] select = new DataSet[0];
        try {
            select = new Select(connection.createStatement()).select(this.select);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        System.out.println(Arrays.toString(select));
    }
}
