package connection;

import exeption.InvalidException;
import java.sql.*;

public class DBconnection {
    private String dbname;
    private String username;
    private String password;
    private String url;

    public DBconnection(String dbname, String username, String password) throws SQLException {
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        this.url = "jdbc:postgresql://localhost:5432/" + dbname;
    }

    public boolean checkParametrs(){

        //TODO добавить проверки на ошибочные параметры
        // проверка на null
            if(dbname == null || username == null || password == null || dbname == null) return false;
            else return true;
    }

    public boolean dbConnection() throws SQLException, ClassNotFoundException, InvalidException {

        if(!checkParametrs()) throw new InvalidException("Invalid incoming parameter:" + " dbname: "+ dbname + "username: " + username + " password: " + password);
        else{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection success to: " + dbname);
        }
        return true;
    }
        //TODO доваить реализацию закрытия моединения
}

