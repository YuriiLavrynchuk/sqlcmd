package integration;

import controller.MainController;
import exeption.InvalidException;
import model.DBconnection;
import org.junit.Before;
import org.junit.Test;
import view.DataInOut;

import java.io.PrintStream;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private static ConfigurableInputStream in;
    private static LogOutputStream out;

    @Before
    public /*static*/ void setup() throws InvalidException {
        in = new ConfigurableInputStream();
        out = new LogOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));
    }
    @Test
    public void testExit() throws InvalidException {
        Connection connectToDB = new DBconnection("postgres", "postgres", "1234").dbConnection();
        in.add("help");
        in.add("exit");

        new MainController((DataInOut)in, connectToDB).start();
        assertEquals("", out.getData());
    }
}
