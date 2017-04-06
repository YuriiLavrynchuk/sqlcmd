package controller;

import exeption.InvalidException;
import model.DBconnection;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Yuriy Lavrinchuk on 03.04.2017.
 */
public class ConnectToDB implements Command {

    private String dbname;
    private String username;
    private String password;

    public ConnectToDB(String db, String user, String pass) {
        this.dbname = db;
        this.username = user;
        this.password = pass;
    }

        @Override
    public void execute() throws SQLException, InvalidException, ClassNotFoundException {
//        DBconnection connect = new DBconnection();
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
}
