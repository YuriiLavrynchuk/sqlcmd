import controller.CommandConnectToDB;
import exeption.InvalidException;
import model.SelectClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvalidException {
        Connection connectToDB = new CommandConnectToDB("postgres", "postgres", "1234").execute();
        Statement st = connectToDB.createStatement();

//        DeleteClass del = new DeleteClass(st);
//        InsertClass ins = new InsertClass(st);
//        UpdateClass up = new UpdateClass(st);
        SelectClass select = new SelectClass(st);

        st.close();
        connectToDB.close();
    }
}
