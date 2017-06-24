import controller.MainController;
import exeption.InvalidException;
import view.Console;
import view.DataInOut;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InvalidException, SQLException {
//        ExConnectToDB connect = new ExConnectToDB("postgres", "postgres", "1234");
//        ExSelect select = new ExSelect(connect.getDbname(), connect.getUsername(), connect.getPassword()).select();

        DataInOut dataInOut = new Console();
//        Connection connection = new ExConnectToDB(dataInOut);
        new MainController(dataInOut).start();
    }
}
