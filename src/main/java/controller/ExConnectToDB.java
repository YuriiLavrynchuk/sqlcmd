package controller;

import model.DBconnection;

import java.sql.Connection;

public class ExConnectToDB  implements ExecuteConnect{
    private String url = "jdbc:postgresql://localhost:5432/";
    private final String dbname;
    private final String username;
    private final String password;
    private Connection connection;

    public ExConnectToDB(String db, String user, String pass) {
        this.dbname = db;
        this.username = user;
        this.password = pass;
        this.url = url + db + "?loggerLevel=OFF";
    }

    @Override
    public void connect() {
        try {
            connection = new DBconnection(ExConnectToDB.this).dbConnection();
        } catch (Exception e) {
//            e.printStackTrace();
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
    public Connection getConnection(){
        connect();
        return connection;
    }
}
