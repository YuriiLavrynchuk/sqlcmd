import controller.ExConnectToDB;
import exeption.InvalidException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class DBtest {
    Connection connectToDB;
    @Before
    public void testGetAllTables() throws SQLException, InvalidException, ClassNotFoundException {
         new ExConnectToDB("postgres", "postgres", "1234").connect();
    }

//    @Test
//    public void testSelect() throws SQLException {
//        Statement st = connectToDB.createStatement();
//        Select select = new Select(st);
//        st.close();
//        connectToDB.close();
//    }
//
//    @Test
//    public void testDelete() throws SQLException {
//        Statement st = connectToDB.createStatement();
//        Delete del = new Delete(st);
//        st.close();
//        connectToDB.close();
//    }
//
//    @Test
//    public void testUpdate() throws SQLException {
//        Statement st = connectToDB.createStatement();
//        Update up = new Update(st);
//        st.close();
//        connectToDB.close();
//    }
//
//    @Test
//    public void testInsert() throws SQLException {
//        Statement st = connectToDB.createStatement();
//        Insert insert = new Insert(st);
//        st.close();
//        connectToDB.close();
//    }

    @Test
    public void testSelectAllTables() throws SQLException {
        Statement st = connectToDB.createStatement();
        String[] selectall = new SelectTablesList(st).SelectAllTable();
        assertEquals("[users]", Arrays.toString(selectall));
        st.close();
        connectToDB.close();
    }
}
