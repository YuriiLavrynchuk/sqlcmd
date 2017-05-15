package controller;

import model.Delete;

import java.sql.Connection;
import java.sql.SQLException;

public class ExDelete {
    private Connection connection;
    private String deletemsg;

    public ExDelete(Connection connection, String deletemsg){
        this.connection = connection;
        this.deletemsg = deletemsg;
    }

    public ExDelete delete(){
        try {
            Delete delete = new Delete(connection.createStatement(), deletemsg);
        } catch (SQLException e) {
            System.out.println("ExDelete delete ERROR");
//            e.printStackTrace();
        }
            return null;
    }
}

