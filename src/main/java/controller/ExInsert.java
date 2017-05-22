package controller;

import model.Insert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ExInsert{
    private final Connection connection;
    private final String insertmsg;

    public ExInsert(Connection connection, String insertmsg) {
        this.connection = connection;
        this.insertmsg = insertmsg;
    }

    public void insert(){
        try (Statement statement = connection.createStatement()){
            new Insert(statement, insertmsg);
        } catch (SQLException e) {
            System.out.println("ExInsert insert ERROR");
//            e.printStackTrace();
        }
    }
}
