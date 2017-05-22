package controller;

import model.Insert;

import java.sql.Connection;
import java.sql.SQLException;

class ExInsert{
    private final Connection connection;
    private final String insertmsg;

    public ExInsert(Connection connection, String insertmsg) {
        this.connection = connection;
        this.insertmsg = insertmsg;
    }

    public void insert(){
        try {
            new Insert(connection.createStatement(), insertmsg);
        } catch (SQLException e) {
            System.out.println("ExInsert insert ERROR");
//            e.printStackTrace();
        }
    }
}
