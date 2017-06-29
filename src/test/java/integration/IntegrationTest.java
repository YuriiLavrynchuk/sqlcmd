package integration;

import controller.Main;
import exeption.InvalidException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private static ConfigurableInputStream in;
    private static ByteArrayOutputStream out;

    @BeforeClass
    public static void setup() throws InvalidException {

        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testHelp() {

        in.add("help");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //help
                "Exist command:\r\n" +
                "connect       - connection to database\r\n" +
                "select        - query from table\r\n" +
                "tablelist     - getting names all tables\r\n" +
                "update        - update rows in the table\r\n" +
                "insert        - insert new row in the table\r\n" +
                "delete        - delete row from table\r\n" +
                "exit          - exit from application\r\n" +
                "get columns   - get columns from table\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }


    @Test
    public void testExit() {

        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testTableListWithoutConnect() {

        in.add("tablelist");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //tablelist
                "You can't use command 'tablelist', please first connect to database using command 'connect'\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testSelectWithoutConnect() {

        in.add("select");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //select
                "You can't use command 'select', please first connect to database using command 'connect'\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testDeleteWithoutConnect() {

        in.add("delete");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //delete
                "You can't use command 'delete', please first connect to database using command 'connect'\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testGetColumnsWithoutConnect() {

        in.add("get columns");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //get columns
                "You can't use command 'get columns', please first connect to database using command 'connect'\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testUpdateWithoutConnect() {

        in.add("update");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //update
                "You can't use command 'update', please first connect to database using command 'connect'\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testNoExistCommandWithoutConnect() {

        in.add("xxxxxx");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //tablelist
                "You can't use command 'xxxxxx', please first connect to database using command 'connect'\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testInsertWithoutConnect() {

        in.add("insert");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //tablelist
                "You can't use command 'insert', please first connect to database using command 'connect'\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testNoExistCommandAfterConnect() {
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("notexistcommand");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or help:\r\n" +
                //notexistcommand
                "Not exists command.\r\n" +
                //exit
                "Please enter command or help:\r\n" +
                "Good by!\r\n", getData());
    }

    @Test
    public void testTableListAfterConnect() {
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("tablelist");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or help:\r\n" +
                //tablelist
                "[users]\r\n" +
                //exit
                "Please enter command or help:\r\n" +
                "Good by!\r\n", getData());
    }

    @Test
    public void testSelectAfterConnect() {
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("select");
        in.add("users where id = 5");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                "Please insert dbname:\r\n" +
                "Please insert username:\r\n" +
                "Please insert password:\r\n" +
                "Connection success!\r\n" +
                "Please enter command or help:\r\n" +
                "Enter tablename:\r\n" +

                "[DataSet{\r\n" +
                "names:[id, name, password]\r\n" +
                "values:[5, User5, 9999]\r\n" +
                "}]\r\n" +
                "Please enter command or help:\r\n" +
                "Good by!\r\n", getData());
    }

    public String getData() {
        try {
            return new String(out.toByteArray(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }


}
