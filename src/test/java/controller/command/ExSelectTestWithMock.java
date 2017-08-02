package controller.command;

import controller.*;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import view.DataInOut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class ExSelectTestWithMock {
    private DbConnection dbConnection;
    private Statement statement;
    private DataInOut dataInOut;
    private Select select;
    private Command command;

    @Before
    public void setup() throws SQLException {
        dataInOut = mock(DataInOut.class);
        statement = mock(Statement.class);
        dbConnection = mock(DbConnection.class);
        select = mock(Select.class);
        command = new ExSelect(dataInOut, dbConnection, select);
    }

    @Test
    public void testExSelect() throws Exception {

        //given

        List<String> datalist = new ArrayList();
        datalist.add("1, User1, 1111");
        datalist.add("2, User2, 2222");

        //when
        when(dataInOut.inPut()).thenReturn("users");
        when(dbConnection.getStatement()).thenReturn(statement);
        when(select.getTableColumns("users", statement)).thenReturn(new String[]{"id", "name", "password"});
        when(select.select("users", statement)).thenReturn(datalist);

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
        List<String> datalist = new ArrayList();

        //when
        when(dataInOut.inPut()).thenReturn("users");
        when(dbConnection.getStatement()).thenReturn(statement);
        when(select.getTableColumns("users", statement)).thenReturn(new String[]{"id", "name", "password"});
        when(select.select("users", statement)).thenReturn(datalist);

        command.execute("select");

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(dataInOut, atLeastOnce()).outPut(captor.capture());
        assertEquals("[Enter tablename:" +
                ", --------------------," +
                " |id|name|password|," +
                " --------------------," +
                " --------------------]", captor.getAllValues().toString());
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
//        DataSet user1 = new DataSet();
//        user1.put("id", 1);
//
//        DataSet user2 = new DataSet();
//        user2.put("id", 2);
//
//        DataSet[] data = new DataSet[]{user1, user2};
        List<String> datalist = new ArrayList();
        //when
        when(dataInOut.inPut()).thenReturn("users");
        when(dbConnection.getStatement()).thenReturn(statement);
        when(select.getTableColumns("users", statement)).thenReturn(new String[]{"id"});
        when(select.select("users", statement)).thenReturn(datalist);

        command.execute("select");

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(dataInOut, atLeastOnce()).outPut(captor.capture());
        assertEquals("[Enter tablename:" +
                ", --------------------," +
                " |id|," +
                " --------------------," +
                " |1|," +
                " |2|," +
                " --------------------]", captor.getAllValues().toString());
    }
}
