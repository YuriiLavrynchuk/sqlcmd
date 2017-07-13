package controller.command;

import controller.*;
import exeption.ExitException;
import org.junit.Test;
import org.mockito.Mockito;
import view.DataInOut;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class ExitWithMockitoTest {

    private DataInOut dataInOut = Mockito.mock(DataInOut.class);

    @Test
    public void testcheckCommandExitPrint() {
        //given
        Command command = new ExExit(dataInOut);

        //when
        boolean checkCommand = command.checkCommand("exit");

        //then
        assertTrue(checkCommand);
    }

    @Test
    public void testcheckCommandqqqPrint() {
        //given
        Command command = new ExExit(dataInOut);

        //when
        boolean checkCommand = command.checkCommand("qqq");

        //then
        assertFalse(checkCommand);
    }

    @Test
    public void testExecuteExitCommand_throwsExitException(){
        //given
        Command command = new ExExit(dataInOut);

        //when
        try {
            command.execute("exit");
            fail("Expected ExitException");
        } catch (ExitException e){
            //do nothing
        }
        //then
        Mockito.verify(dataInOut).outPut("Good by!");
        //throws ExitException
    }
}
