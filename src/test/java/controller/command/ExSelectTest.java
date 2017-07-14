package controller.command;

import controller.ExSelect;
import model.DataSet;
import model.DbConnection;
import model.Select;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import view.DataInOut;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;

public class ExSelectTest {
    private DbConnection dbConnection;
    private DataInOut dataInOut;
    private Statement statement;
    private ResultSet resultSet;
    private Select select;
    private ExSelect command;

    @Before
    public void setup() throws SQLException {

        dbConnection = Mockito.mock(DbConnection.class);
        dataInOut = Mockito.mock(DataInOut.class);
        resultSet = Mockito.mock(ResultSet.class);
        statement = Mockito.mock(Statement.class);
        select = Mockito.mock(Select.class);
    }

    @Test
    public void test() throws SQLException{

        //given
        command = new ExSelect(dataInOut, dbConnection);

        DataSet user1 = new DataSet();
        user1.put("id", 1);
        user1.put("name", "User1");
        user1.put("password", "1111");

        DataSet user2 = new DataSet();
        user2.put("id", 2);
        user2.put("name", "User2");
        user2.put("password", "2222");

        DataSet[] data = new DataSet[]{user1, user2};
        String[] columnNames = {"id", "name", "password"};

        //when
        Mockito.when(dataInOut.inPut()).thenReturn("users");
        Mockito.when(dbConnection.getStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(anyString())).thenReturn(resultSet);

        Mockito.when(select.getTableColumns("users")).thenReturn(columnNames);
        Mockito.when(select.select("users")).thenReturn(data);
//        Mockito.when(command.printHeader(columnNames)).thenCallRealMethod(dataInOut.outPut(String.valueOf(columnNames)));

        command.execute("select");


//        Mockito.when(select.getTableColumns(anyString())).thenReturn(new String[]{"id", "name", "password"});


//        Mockito.when(selectMsg.indexOf(selectMsg)).thenReturn(1);

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(dataInOut, atLeastOnce()).outPut(captor.capture());
        assertEquals("", captor.getAllValues().toString());
    }
}
