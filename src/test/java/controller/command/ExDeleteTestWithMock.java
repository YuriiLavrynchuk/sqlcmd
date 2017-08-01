package controller.command;

import controller.Command;
import controller.ExDelete;
import model.DbConnection;
import model.Delete;
import org.junit.Before;
import org.junit.Test;
import view.DataInOut;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ExDeleteTestWithMock {
    private DbConnection dbConnection;
    private DataInOut dataInOut;
    private Delete delete;
    private Command command;

    @Before
    public void setup() throws SQLException {
        dataInOut = mock(DataInOut.class);
        dbConnection = mock(DbConnection.class);
        delete = mock(Delete.class);
        command = new ExDelete(dataInOut, dbConnection, delete);
    }

    @Test
    public void testExDelete() throws Exception {
        //when
        when(dataInOut.inPut()).thenReturn("delete from 'users'");

        command.execute("delete");

        //then
        verify(dataInOut).outPut("Enter Delete query in format -> delete from tablename where column = 'value'\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
    }
}
