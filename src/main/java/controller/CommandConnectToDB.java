package controller;

import exeption.InvalidException;
import model.DBconnection;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Yuriy Lavrinchuk on 03.04.2017.
 */
public class CommandConnectToDB implements Command {

    private String url = "jdbc:postgresql://localhost:5432/";
    private String dbname;
    private String username;
    private String password;

    public CommandConnectToDB(String db, String user, String pass) {
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
    public Statement execute() throws SQLException, InvalidException, ClassNotFoundException {
            Statement connect = new DBconnection(CommandConnectToDB.this).dbConnection();
            return connect;
        }
}
