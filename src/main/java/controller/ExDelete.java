package controller;

import model.Delete;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ExDelete {
    private final Connection connection;
    private final String deletemsg;

    public ExDelete(Connection connection, String deletemsg){
        this.connection = connection;
        this.deletemsg = deletemsg;
    }

    public void delete(){
        try (Statement statement = connection.createStatement()){
            new Delete(statement, deletemsg);
        } catch (SQLException e) {
            System.out.println("ExDelete delete ERROR");
//            e.printStackTrace();
        }
    }
}

