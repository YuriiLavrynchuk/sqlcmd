package model;

import dnl.utils.text.table.TextTable;
import exeption.InvalidException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TablePrinterTest {
    private Connection connectToDB;
    private Statement st;

    @Before
    public void testGetAllTables() throws SQLException, InvalidException, ClassNotFoundException {
        connectToDB = new DbConnection().connection("postgres", "postgres", "1234");
        st = connectToDB.createStatement();
    }

    @Test
    public void testTablePrinter() throws SQLException {
        st = connectToDB.createStatement();
        List<String> select = new Select().select("users", st);

        String[] tableColumns = new String[]{"id", "name", "password"};
        String[][] x = new String[1][];
        x[0] = select.get(0).split(", ");

        TextTable tt = new TextTable(tableColumns, x);
//        tt.printTable();

//        PrintStream ps = new PrintStream(baos);
        assertEquals("[DataSet{\n" +
                "names:[id, name, password]\n" +
                "values:[5, User5, 9999]\n" +
                "}]", select.get(1).substring(1));
        st.close();
    }
}
