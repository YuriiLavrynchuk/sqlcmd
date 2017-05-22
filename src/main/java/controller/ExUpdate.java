package controller;

import model.Update;

import java.sql.Connection;
import java.sql.SQLException;

class ExUpdate {
    private final Connection connection;
    private final String updatemsg;

    public ExUpdate(Connection connection, String updatemsg) {
        this.connection = connection;
        this.updatemsg = updatemsg;
    }

    public void update(){
        try {
            new Update(connection.createStatement(), updatemsg);
        } catch (SQLException e) {
            System.out.println("ExUpdate update ERROR");
//            e.printStackTrace();
        }
    }
}
