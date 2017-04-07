import controller.CommandConnectToDB;
import exeption.InvalidException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Admin on 06.04.2017.
 */
public class DBtest {
    Connection connectToDB;
    @Before
    public void testGetAllTables() throws SQLException, InvalidException, ClassNotFoundException {
         connectToDB = new CommandConnectToDB("postgres", "postgres", "1234").execute();
    }

//    @Test
//    public void testSelect() throws SQLException {
//        Statement st = connectToDB.createStatement();
//        SelectClass select = new SelectClass(st);
//        connectToDB.close();
//    }

//    @Test
//    public void testDelete() throws SQLException {
//        Statement st = connectToDB.createStatement();
//        DeleteClass del = new DeleteClass(st);
//        connectToDB.close();
//    }

//    @Test
//    public void testUpdate() throws SQLException {
//        Statement st = connectToDB.createStatement();
//        UpdateClass up = new UpdateClass(st);
//        connectToDB.close();
//    }

//    @Test
//    public void testInsert() throws SQLException {
//        Statement st = connectToDB.createStatement();
//        InsertClass insert = new InsertClass(st);
//        connectToDB.close();
//    }

    @Test
    public void testSelectAllTables() throws SQLException {
        Statement st = connectToDB.createStatement();
        String[] selectall = new SelectAllTableClass(st).SelectAllTable();
        assertEquals("[users]", Arrays.toString(selectall));
        connectToDB.close();
    }
}
