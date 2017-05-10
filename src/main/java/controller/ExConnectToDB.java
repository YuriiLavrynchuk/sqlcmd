package controller;

import model.DBconnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ExConnectToDB  implements ExecuteConnect{
    private String url = "jdbc:postgresql://localhost:5432/";
    private String dbname;
    private String username;
    private String password;
    private Connection connect;

    public ExConnectToDB(String db, String user, String pass) {
        this.dbname = db;
        this.username = user;
        this.password = pass;
        this.url = url + db;
    }

    @Override
    public void connect() {
        try {
            connect = new DBconnection(ExConnectToDB.this).dbConnection();
        } catch (Exception e) {
//            e.printStackTrace();
            try {
                connect.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public String getUrl() {
        return url;
    }
    public String getDbname() {
        return dbname;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Connection getConnect(){
        connect();
        return connect;
    }
}
