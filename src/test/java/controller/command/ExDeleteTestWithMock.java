package controller.command;

import controller.Command;
import controller.ExDelete;
import model.DbConnection;
import model.InsertUpdateDeleteCreate;
import org.junit.Before;
import org.junit.Test;
import view.DataInOut;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ExDeleteTestWithMock {
    private DbConnection dbConnection;
    private DataInOut dataInOut;
    private InsertUpdateDeleteCreate crud;
    private Command command;

    @Before
    public void setup() throws SQLException {
        dataInOut = mock(DataInOut.class);
        dbConnection = mock(DbConnection.class);
        crud = mock(InsertUpdateDeleteCreate.class);
        command = new ExDelete(dataInOut, dbConnection, crud);
    }

    @Test
    public void testExDelete() throws Exception {
        //when
        when(dataInOut.inPut()).thenReturn("delete from 'users'");

        command.execute("delete");

        //then
        verify(dataInOut).outPut("Enter Delete query in format ->\r\n" +
                "delete from tablename where column = 'value'\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
    }
}
