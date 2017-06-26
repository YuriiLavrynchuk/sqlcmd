package integration;

import controller.*;
import exeption.InvalidException;
import model.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private static ConfigurableInputStream in;
    private static LogOutputStream out;
    private static DBconnection dBconnection;
    private static Connection connection;

    @BeforeClass
    public static void setup() throws InvalidException {

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
