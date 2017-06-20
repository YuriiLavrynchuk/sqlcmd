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
        connectToDB = new DBconnection("postgres", "postgres", "1234").dbConnection();
        st = connectToDB.createStatement();
    }

    @Test
    public void testSelect() throws SQLException {

        DataSet[] select = new Select(st).select("users");
        assertEquals(5, select.length);
        st.close();
    }

    @Test
    public void testGetcolumns() throws SQLException {

        String[] columns = new Select(st).getTableColumns("users");
        assertEquals("[id, name, password]", Arrays.toString(columns));
        st.close();
    }

    @Test
    public void testSelectAllTables() throws SQLException {

        String[] selectall = new SelectTablesList(st).selectAllTable();
        assertEquals("[users]", Arrays.toString(selectall));
        st.close();
    }

    @Test
    public void testCRUD() throws SQLException {
        //delete
        st = connectToDB.createStatement();
        new Delete(st, "delete from users where id = 5");
        st.close();

        //insert
        st = connectToDB.createStatement();
        new Insert(st, "insert into users values(5, 'User5', 5555)");
        st.close();

        //update
        st = connectToDB.createStatement();
        new Update(st, "update users set password = '9999' where id = 5");
        st.close();

        st = connectToDB.createStatement();
        DataSet[] select = new Select(st).select("users where id = 5");
        assertEquals("[DataSet{\n" +
                "names:[id, name, password]\n" +
                "values:[5, User5, 9999]\n" +
                "}]", Arrays.toString(select));
        st.close();
    }
}
