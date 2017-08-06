package integration;

import controller.Main;
import exeption.InvalidException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import model.DataBaseResources;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    public static final String DB_NAME_TEST = "test";
    public static final String USER_NAME_TEST = "admin";
    public static final String PASSWORD_TEST = "pass";
    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;

    @BeforeClass
    public static void setupDataBase() throws SQLException, InvalidException {
        new DataBaseResources().before();
    }

    @AfterClass
    public static void dropDataBase() throws SQLException, InvalidException {
        new DataBaseResources().after();
    }

    @Before
    public void setup() throws InvalidException {
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testHelp(){

        in.add("help");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //help
                "You should use commandwords and follow the messages in the console\r\n" +
                "Exist commands:\r\n" +
                "connect       - connection to database\r\n" +
                "select        - query from table\r\n" +
                "tablelist     - getting names all tables\r\n" +
                "update        - update rows in the table\r\n" +
                "insert        - insert new row in the table\r\n" +
                "delete        - delete row from table\r\n" +
                "exit          - exit from application\r\n" +
                "get columns   - get columns from table\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testExit(){

        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testTableListWithoutConnect(){

        in.add("tablelist");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //tablelist
                "You can't use command 'tablelist', please first connect to database using command 'connect'\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testSelectWithoutConnect(){

        in.add("select");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //select
                "You can't use command 'select', please first connect to database using command 'connect'\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testDeleteWithoutConnect(){

        in.add("delete");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //delete
                "You can't use command 'delete', please first connect to database using command 'connect'\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testGetColumnsWithoutConnect(){

        in.add("get columns");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //get columns
                "You can't use command 'get columns', please first connect to database using command 'connect'\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testUpdateWithoutConnect(){

        in.add("update");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //update
                "You can't use command 'update', please first connect to database using command 'connect'\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testNoExistCommandWithoutConnect(){

        in.add("xxxxxx");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //tablelist
                "You can't use command 'xxxxxx', please first connect to database using command 'connect'\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testInsertWithoutConnect(){

        in.add("insert");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //tablelist
                "You can't use command 'insert', please first connect to database using command 'connect'\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testNoExistCommandAfterConnect(){
        in.add("connect");
        in.add(DB_NAME_TEST);
        in.add(USER_NAME_TEST);
        in.add(PASSWORD_TEST);
        in.add("notexistcommand");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or 'help':\r\n" +
                //notexistcommand
                "Not exists command.\r\n" +
                //exit
                "Please enter command or 'help':\r\n" +
                "Good by!\r\n", getData());
    }

    @Test
    public void testTableListAfterConnect(){
        in.add("connect");
        in.add(DB_NAME_TEST);
        in.add(USER_NAME_TEST);
        in.add(PASSWORD_TEST);
        in.add("tablelist");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or 'help':\r\n" +
                //tablelist
                "[users]\r\n" +
                //exit
                "Please enter command or 'help':\r\n" +
                "Good by!\r\n", getData());
    }

    @Test
    public void testSelectAfterConnect(){
        in.add("connect");
        in.add(DB_NAME_TEST);
        in.add(USER_NAME_TEST);
        in.add(PASSWORD_TEST);
        in.add("select");
        in.add("users");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or 'help':\r\n" +
                //select
                "Enter tablename:\r\n" +
                //tablename
                "_____________________\r\n" +
                "| id| name | password|\n" +
                "|====================|\r\n" +
                "| 1 | Admin| 1111    |\n" +
                "| 2 | User2| 4532    |\n" +
                "| 3 | User3| 6443    |\n" +
                "| 4 | User4| 4444    |\n" +
                "| 5 | User5| 9999    |\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testDeleteWithErrorAfterConnect(){
        in.add("connect");
        in.add(DB_NAME_TEST);
        in.add(USER_NAME_TEST);
        in.add(PASSWORD_TEST);
        in.add("delete");
        in.add("doloto fram");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or 'help':\r\n" +
                //delete
                "Enter Delete query in format -> delete from tablename where column = 'value'\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'\r\n" +
                //deletemsg
                "Wrong query: doloto fram\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testUpdateWithErrorAfterConnect(){
        in.add("connect");
        in.add(DB_NAME_TEST);
        in.add(USER_NAME_TEST);
        in.add(PASSWORD_TEST);
        in.add("update");
        in.add("updotx");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or 'help':\r\n" +
                //update
                "Enter Update query in format -> update tablename set column = value where column_n = value_n\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'\r\n" +
                //updatemsg
                "Wrong query: updotx\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testInsertWithErrorAfterConnect(){
        in.add("connect");
        in.add(DB_NAME_TEST);
        in.add(USER_NAME_TEST);
        in.add(PASSWORD_TEST);
        in.add("insert");
        in.add("insr");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or 'help':\r\n" +
                //insert
                "Enter Insert query in format -> insert into tablename values(value_1,... value_n)\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'\r\n" +
                //insertmsg
                "Wrong query: insr\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testGetColumnsAfterConnect(){
        in.add("connect");
        in.add(DB_NAME_TEST);
        in.add(USER_NAME_TEST);
        in.add(PASSWORD_TEST);
        in.add("get columns");
        in.add("users");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or 'help':\r\n" +
                //get columns
                "Enter tablename:\r\n" +
                //updatemsg
                "[id, name, password]\r\n" +
                "Please enter command or 'help':\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testConnectWithWrongParametr(){
        in.add("connect");
        in.add(DB_NAME_TEST);
        in.add(USER_NAME_TEST);
        //wrong password
        in.add("0000");
        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect' or enter 'help'\r\n" +
                //conncet
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "FAIL! Cause:\r\n" +
                "FATAL: password authentication failed for user \"admin\"\r\n" +
                "Can't get connection to database:test\r\n" +
                "Try again.\r\n" +
                //exit
                "Please enter command or 'help':\r\n" +
                "Good by!\r\n", getData());
    }

    private String getData(){
        try {
            String result = new String(out.toByteArray(), "UTF-8");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e){
            return e.getMessage();
        }
    }
}
