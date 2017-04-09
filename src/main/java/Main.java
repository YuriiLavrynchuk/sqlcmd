import controller.ExSelect;
import exeption.InvalidException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InvalidException, SQLException {

        ExSelect select = new ExSelect("postgres", "postgres", "1234").select();
    }
}
