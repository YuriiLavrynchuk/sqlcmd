package controller;

import model.DataSet;
import model.Select;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

class ExSelect {
    private final Connection connection;
    private final String selectmsg;

    public ExSelect(Connection connection, String selectmsg){
        this.connection = connection;
        this.selectmsg = selectmsg;
    }

    public void select(){
        DataSet[] select = new DataSet[0];
        try (Statement statement = connection.createStatement()){
            select = new Select(statement).select(selectmsg);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        System.out.println(Arrays.toString(select));
    }
}
