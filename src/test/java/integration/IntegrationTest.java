package integration;

import controller.Main;
import exeption.InvalidException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;

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
    public void testExit(){

        in.add("exit");

        Main.main(new String[0]);

        assertEquals("Hello!\r\n" +
                "Please connect to database using command 'connect'\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testTableListWithoutConnect(){

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
    public void testSelectWithoutConnect(){

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
    public void testDeleteWithoutConnect(){

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
    public void testGetColumnsWithoutConnect(){

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
    public void testUpdateWithoutConnect(){

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
    public void testNoExistCommandWithoutConnect(){

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
    public void testInsertWithoutConnect(){

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
    public void testNoExistCommandAfterConnect(){
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
    public void testTableListAfterConnect(){
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
    public void testSelectAfterConnect(){
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("select");
        in.add("users");
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
                //select
                "Enter tablename:\r\n" +
                //tablename
                "--------------------\r\n" +
                "|id|name|password|\r\n" +
                "--------------------\r\n" +
                "|1|Admin|1111|\r\n" +
                "|2|User2|4532|\r\n" +
                "|3|User3|6443|\r\n" +
                "|4|User4|4444|\r\n" +
                "|5|User5|9999|\r\n" +
                "--------------------\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testConnectAfterConnect(){
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("tablelist");
        in.add("connect");
        in.add("test");
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
                "Please enter command or help:\r\n" +
                //connect
                "Please insert dbname:\r\n" +
                //dbname
                "Please insert username:\r\n" +
                //username
                "Please insert password:\r\n" +
                //password
                "Connection success!\r\n" +
                "Please enter command or help:\r\n" +
                //tablelist
                "[test]\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testDeleteWithErrorAfterConnect(){
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("delete");
        in.add("del");
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
                //delete
                "Enter Delete query:\r\n" +
                //deletemsg
                "FAIL! Cause:\r\n" +
                "ERROR: syntax error at or near \"del\"\n" +
                "  Позиция: 1\r\n" +
                "ExDelete delete ERROR\r\n" +
                "Try again.\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testUpdateWithErrorAfterConnect(){
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("update");
        in.add("updt");
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
                //update
                "Enter Update query:\r\n" +
                //updatemsg
                "FAIL! Cause:\r\n" +
                "ERROR: syntax error at or near \"updt\"\n" +
                "  Позиция: 1\r\n" +
                "Update ERROR\r\n" +
                "Try again.\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testInsertWithErrorAfterConnect(){
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("insert");
        in.add("insr");
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
                //insert
                "Enter Insert query:\r\n" +
                //insertmsg
                "FAIL! Cause:\r\n" +
                "ERROR: syntax error at or near \"insr\"\n" +
                "  Позиция: 1\r\n" +
                "ExInsert insert ERROR\r\n" +
                "Try again.\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testGetColumnsAfterConnect(){
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("1234");
        in.add("get columns");
        in.add("users");
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
                //get columns
                "Enter tablename:\r\n" +
                //updatemsg
                "[id, name, password]\r\n" +
                "Please enter command or help:\r\n" +
                //exit
                "Good by!\r\n", getData());
    }

    @Test
    public void testConnectWithWrongParametr(){
        in.add("connect");
        in.add("postgres");
        in.add("postgres");
        in.add("0000");
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
                "FAIL! Cause:\r\n" +
                "FATAL: password authentication failed for user \"postgres\"\r\n" +
                "Can't get connection to database:postgres\r\n" +
                "Try again.\r\n" +
                //exit
                "Please enter command or help:\r\n" +
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
