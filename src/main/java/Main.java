import controller.ExConnectToDB;
import controller.ExSelect;
import exeption.InvalidException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InvalidException, SQLException {
        ExConnectToDB connect = new ExConnectToDB("postgres", "postgres", "1234");
        ExSelect select = new ExSelect(connect.getDbname(), connect.getUsername(), connect.getPassword()).select();
    }
}
