package integration;

import controller.*;
import exeption.InvalidException;
import model.DBconnection;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private static ConfigurableInputStream in;
    private static LogOutputStream out;

    @Before
    public static void setup(){
        in = new ConfigurableInputStream();
        out = new LogOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));
    }
    @Test
    public void testExit() {

        in.add("help");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("", out.getData());
    }
}
