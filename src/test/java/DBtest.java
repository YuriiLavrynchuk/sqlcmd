import exeption.InvalidException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class DBtest {
     private Connection connectToDB;
     private Statement st;
     private DataInOut dataInOut;

    @Before
    public void testGetAllTables() throws SQLException, InvalidException, ClassNotFoundException {
        connectToDB = new DBconnection().connection("postgres", "postgres", "1234");
        st = connectToDB.createStatement();
    }

    @Test
    public void testSelect() throws SQLException {

        DataSet[] select = new Select(st, dataInOut).select("users");
        assertEquals(5, select.length);
        st.close();
    }

    @Test
    public void testGetcolumns() throws SQLException {

        String[] columns = new Select(st, dataInOut).getTableColumns("users");
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
    public void testInsertUsingPut() throws SQLException {
        st = connectToDB.createStatement();
        new Delete(st, "delete from users where id = 7");
        st.close();
//        // when
//        DataSet input = new DataSet();
//        input.put("name", "Stiven");
//        input.put("password", "pass");
//        input.put("id", 13);
//        manager.create("user", input);
//
//        // then
//        DataSet[] users = manager.getTableData("user");
//        assertEquals(1, users.length);
//
//        DataSet user = users[0];
//        assertEquals("[name, password, id]", Arrays.toString(user.getNames()));
//        assertEquals("[Stiven, pass, 13]", Arrays.toString(user.getValues()));

        st = connectToDB.createStatement();
        DataSet input = new DataSet();
        input.put("id", 7);
        input.put("name", "User7");
        input.put("password", "7777");
        new Insert(st, "users", input);
        st.close();

          // then
        st = connectToDB.createStatement();
        DataSet[] users = new Select(st, dataInOut).select("users where id=7");
        assertEquals(1, users.length);
        st.close();

        DataSet user = users[0];
        assertEquals("[id, name, password]", Arrays.toString(user.getNames()));
        assertEquals("[7, User7, 7777]", Arrays.toString(user.getValues()));

        st = connectToDB.createStatement();
        new Delete(st, "delete from users where id = 7");
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
        DataSet[] select = new Select(st, dataInOut).select("users where id = 5");
        assertEquals("[DataSet{\n" +
                "names:[id, name, password]\n" +
                "values:[5, User5, 9999]\n" +
                "}]", Arrays.toString(select));
        st.close();
    }
}
