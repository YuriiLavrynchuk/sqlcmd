package controller;

import exeption.InvalidException;
import model.DBconnection;
import model.Select;

import java.sql.Connection;
import java.sql.SQLException;

public class ExConnectToDB implements Command {

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

    public String getUrl() {return url; }
    public String getDbname() {return dbname;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}

    @Override
    public Connection execute() throws InvalidException {
        connect = new DBconnection(ExConnectToDB.this).dbConnection();
            return connect;
    }
}
