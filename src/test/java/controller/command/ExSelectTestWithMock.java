package controller.command;

import controller.Command;
import controller.ExSelect;
import model.DbConnection;
import model.Select;
import org.junit.Before;
import org.junit.Test;
import view.DataInOut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExSelectTestWithMock {
    private DbConnection dbConnection;
    private Statement statement;
    private DataInOut dataInOut;
    private Select select;
    private Command command;
    private List<String> dataList;

    @Before
    public void setup() throws SQLException {
        dataInOut = mock(DataInOut.class);
        statement = mock(Statement.class);
        dbConnection = mock(DbConnection.class);
        select = mock(Select.class);
        command = new ExSelect(dataInOut, dbConnection, select);
        dataList = new ArrayList();
        dataList.add("1, User1, 1111");
        dataList.add("2, User2, 2222");
    }

    @Test
    public void testExSelect() throws Exception {

        //given
        when(dbConnection.getStatement()).thenReturn(statement);
        when(select.getTableColumns("users", statement)).thenReturn(new String[]{"id", "name", "password"});
        when(select.select("users", statement)).thenReturn(dataList);

        //when
        when(dataInOut.inPut()).thenReturn("users");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        command.execute("select");

        //then
        assertEquals("_____________________\r\n" +
                "| id| name | password|\r\n" +
                "|====================|\r\n" +
                "| 1 | User1| 1111    |\r\n" +
                "| 2 | User2| 2222    |\r\n", outContent.toString());
    }

    @Test
    public void testExSelectWithNullTable() throws Exception {

        //given
        List<String> data = new ArrayList();
        when(dbConnection.getStatement()).thenReturn(statement);
        when(select.getTableColumns("users", statement)).thenReturn(new String[]{"id", "name", "password"});
        when(select.select("users", statement)).thenReturn(data);

        //when
        when(dataInOut.inPut()).thenReturn("users");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        command.execute("select");

        //then
        assertEquals("____________________\r\n" +
                "| id| name| password|\r\n" +
                "|===================|\r\n", outContent.toString());
    }

    @Test
    public void testExSelectWithFalseCommand() {
        // when
        boolean canProcess = command.checkCommand("qqqqq");
        // then
        assertFalse(canProcess);
    }

    @Test
    public void testExSelectWithOneColumn() throws Exception {
        //given
        when(dataInOut.inPut()).thenReturn("users");
        when(dbConnection.getStatement()).thenReturn(statement);
        when(select.getTableColumns("users", statement)).thenReturn(new String[]{"id"});
        when(select.select("users", statement)).thenReturn(dataList);

        //when
        when(dataInOut.inPut()).thenReturn("users");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        command.execute("select");

        //then
        assertEquals("____\r\n" +
                "| id|\r\n" +
                "|===|\r\n" +
                "| 1 |\r\n" +
                "| 2 |\r\n", outContent.toString());
    }
}
