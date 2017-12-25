package controller;

/**
 * Класс проверяет комманду и выводит сообщение при введении несуществующей комманды.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import view.DataInOut;

public class ExNotExistCommand implements Command {
    private final DataInOut dataInOut;

    /**
     * Создаёт объект ExNotExistCommand
     * @param dataInOut
     */
    public ExNotExistCommand(DataInOut dataInOut){
        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command){
        return true;
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Not exists command.");
    }
}
