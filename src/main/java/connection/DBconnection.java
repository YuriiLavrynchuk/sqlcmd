package connection;

import exeption.InvalidException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import ua.com.juja.sqlcmd.*;

/**
 * Created by Admin on 27.03.2017.
 */
public class DBconnection {
    private String url;
    private String dbname;
    private String username;
    private String password;

    public DBconnection(String dbname, String username, String password) throws SQLException {
        this.url = "jdbc:postgresql://localhost:5432/" + dbname;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }

    public boolean checkParametrs(){

        //TODO добавить проверки на ошибочные параметры
        // проверка на null
            if(dbname == null || username == null || password == null || dbname == null) return false;
            else return true;
    }

    public DBconnection dbConnection() throws SQLException, ClassNotFoundException, InvalidException {

        if(!checkParametrs()) throw new InvalidException("Invalid incoming parameter:" + " dbname: "+ dbname + "username: " + username + " password: " + password);
        else{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection success to: " + dbname);
        }
        return null;
    }
        //TODO доваить реализацию закрытия моединения
}

