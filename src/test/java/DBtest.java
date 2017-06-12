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
     private Connection connectToDB;
     private Statement st;

    @Before
    public void testGetAllTables() throws SQLException, InvalidException, ClassNotFoundException {
        connectToDB = new ExConnectToDB("postgres", "postgres", "1234").getConnection();
        st = connectToDB.createStatement();
    }

    @Test
    public void testSelect() throws SQLException {

        DataSet[] select = new Select(st).select("users");
        assertEquals(4, select.length);

        st.close();
        System.out.println("Connection close");
        connectToDB.close();
    }
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

        String[] selectall = new SelectTablesList(st).selectAllTable();
        System.out.println(Arrays.toString(selectall));
        assertEquals("[users]", Arrays.toString(selectall));
        st.close();
        System.out.println("Connection close");
        connectToDB.close();
    }
}
