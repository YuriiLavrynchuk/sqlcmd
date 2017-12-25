package controller;

/**
 * Класс в котором создаються объекты комманд необходимые для работы с приложением,
 * а также выполнение введённой коммандой после успешной проверки
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */

/**
 * Импорт исключения, слоя модель, ввода/вывода(слой вьюха)
 */
import exeption.ExitException;
import exeption.InvalidException;
import model.*;
import view.DataInOut;


class MainController {
    private final DataInOut dataInOut;
    private final Command[] commands;

    /**
     * Основной конструктор создающий объект  MainController
     * в котором создаються все объекты комманд и помещаються в масив
     *
     * @param dataInOut объект DataInOut (ввода/вывода)
     * @param dBconnection объект DbConnection подключения к БД
     */
    public MainController(DataInOut dataInOut, DbConnection dBconnection){
        this.dataInOut = dataInOut;

        this.commands = new Command[] {
                new ExConnectToDB(dataInOut, dBconnection),
                new ExHelp(dataInOut),
                new ExExit(dataInOut, dBconnection),
                new CheckConnection(dataInOut, dBconnection),
                new ExTableList(dataInOut, dBconnection, new SelectTablesList()),
                new ExUpdate(dataInOut, dBconnection, new InsertUpdateDeleteCreate()),
                new ExNewTable(dataInOut, dBconnection, new InsertUpdateDeleteCreate()),
                new ExInsert(dataInOut, dBconnection, new InsertUpdateDeleteCreate()),
                new ExDelete(dataInOut, dBconnection, new InsertUpdateDeleteCreate()),
                new ExSelect(dataInOut, dBconnection, new Select()),
                new ExDropTable(dataInOut, dBconnection, new InsertUpdateDeleteCreate()),
                new ExGetColumns(dataInOut, dBconnection, new Select()),
                new ExNotExistCommand(dataInOut)
        };
    }

    /**
     * Публичный метод, который запускает инкапсулированные метод start().
     * Создан для внешнего использования
     * @throws InvalidException
     */
    public void run() throws InvalidException {
        try {
            start();
        } catch (ExitException e){
        }
    }

    /**
     * Метод который проверяет зарускает проверку введённой моммманды
     * и её выполнение при успешной проверке.
     *
     * @throws InvalidException
     * @throws ExitException
     */
    private void start() throws InvalidException, ExitException {

        //приветствие и ввод комманды
        dataInOut.outPut("Hello!");
        dataInOut.outPut("Please connect to database using command 'connect' or enter 'help'");

        //сравнение введённой комманды с элементами массива комманд
        while (true){
            String intPut = dataInOut.inPut();
            for (Command command : commands){
                try {
                    if (command.checkCommand(intPut)){
                        command.execute(intPut);
                        break;
                    }
                } catch (ExitException e1){
                    throw e1;
                } catch (Exception e){
                    throw new InvalidException("ERROR", e);
                }
            }
            dataInOut.outPut("Please enter command or 'help':");
        }
    }
}