package integration;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

public class IntegrationTest {

    private static ConfigurableInputStream in;
    private static LogPrintStream out;

    @Before
    public static void setup() {
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }
    @Test
    public void testExit() {

    }
}
