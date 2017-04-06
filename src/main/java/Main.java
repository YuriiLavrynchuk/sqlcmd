import model.DBconnection;
import exeption.InvalidException;

import java.sql.SQLException;
import java.sql.Statement;
import controller.*;

/**
 * Created by Admin on 22.03.2017.
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvalidException {
//        Statement connect = new DBconnection("postgres", "postgres", "1234").dbConnection();
        ConnectToDB connectToDB = new ConnectToDB("postgres", "postgres", "1234");
    }
}
