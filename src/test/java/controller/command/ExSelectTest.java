package controller.command;

import controller.Command;
import controller.ExSelect;
import model.DataSet;
import model.DbConnection;
import model.Select;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;

public class ExSelectTest {
    private DbConnection dbConnection;
    private DataInOut dataInOut;
    private Statement statement;
    private Select select;

    @Before
    public void setup() throws SQLException {

        dbConnection = Mockito.mock(DbConnection.class);
        dataInOut = Mockito.mock(DataInOut.class);
        statement = dbConnection.getStatement();
        select = Mockito.mock(Select.class);
    }

    @Test
    public void test() throws SQLException{

        //given
        Command command = new ExSelect(dataInOut, dbConnection);

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
        command.execute("select");

        Mockito.when(select.getTableColumns("users")).thenReturn(new String[]{"id", "name", "password"});
        Mockito.when(select.select("users")).thenReturn(data);

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(dataInOut, atLeastOnce()).outPut(captor.capture());
        assertEquals("", captor.getAllValues().toString());
    }
}
