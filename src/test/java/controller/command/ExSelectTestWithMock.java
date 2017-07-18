package controller.command;

import controller.*;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import view.DataInOut;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ExSelectTestWithMock {
    private DbConnection dbConnection;
    private DataInOut dataInOut;
    private Select select;

    @Before
    public void setup() throws SQLException {
        dataInOut = mock(DataInOut.class);
        dbConnection = mock(DbConnection.class);
        select = mock(Select.class);
    }

    @Test
    public void test() throws Exception {

        //given
        Command command = new ExSelect(dataInOut, dbConnection, select);

        DataSet user1 = new DataSet();
        user1.put("id", 1);
        user1.put("name", "User1");
        user1.put("password", "1111");

        DataSet user2 = new DataSet();
        user2.put("id", 2);
        user2.put("name", "User2");
        user2.put("password", "2222");

        DataSet[] data = new DataSet[]{user1, user2};

        //when
        when(dataInOut.inPut()).thenReturn("users");
        when(select.getTableColumns("users")).thenReturn(new String[]{"id", "name", "password"});
        when(select.select("users")).thenReturn(data);

        command.execute("select");

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(dataInOut, atLeastOnce()).outPut(captor.capture());
        assertEquals("[Enter tablename:" +
                                ", --------------------," +
                                " |id|name|password|," +
                                " --------------------," +
                                " |1|User1|1111|," +
                                " |2|User2|2222|," +
                                " --------------------]", captor.getAllValues().toString());
    }
}
