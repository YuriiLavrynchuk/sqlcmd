package controller;

import model.Delete;

import java.sql.Connection;
import java.sql.SQLException;

class ExDelete {
    private final Connection connection;
    private final String deletemsg;

    public ExDelete(Connection connection, String deletemsg){
        this.connection = connection;
        this.deletemsg = deletemsg;
    }

    public void delete(){
        try {
            new Delete(connection.createStatement(), deletemsg);
        } catch (SQLException e) {
            System.out.println("ExDelete delete ERROR");
//            e.printStackTrace();
        }
    }
}

