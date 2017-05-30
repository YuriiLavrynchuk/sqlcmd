package controller;

import model.Update;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ExUpdate {
    private final Connection connection;
    private final String updatemsg;

    public ExUpdate(Connection connection, String updatemsg) {
        this.connection = connection;
        this.updatemsg = updatemsg;
    }

    public void update(){
        try(Statement statement = connection.createStatement()) {
            new Update(statement, updatemsg);
        } catch (SQLException e) {
            System.out.println("ExUpdate update ERROR");
//            e.printStackTrace();
        }
    }
}
