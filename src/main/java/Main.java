import connection.DBconnection;
import exeption.InvalidException;

import java.sql.SQLException;
/**
 * Created by Admin on 22.03.2017.
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvalidException {
        DBconnection connect = new DBconnection("postgres", "postgres", "1234").dbConnection();
    }
}
