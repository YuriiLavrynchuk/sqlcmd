import exeption.InvalidException;

import java.sql.SQLException;
import java.sql.Statement;
import controller.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvalidException {
        Statement connectToDB = new CommandConnectToDB("postgres", "postgres", "1234").execute();
    }
}
