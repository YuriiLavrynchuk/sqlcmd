package controller;

/**
 * Интерфейс служит шаблоном для используемых комманд
 *
 * @see CheckConnection
 * @see ExConnectToDB
 * @see ExDelete
 * @see ExDropTable
 * @see ExExit
 * @see ExGetColumns
 * @see ExHelp
 * @see ExInsert
 * @see ExNewTable
 * @see ExSelect
 * @see ExTableList
 * @see ExUpdate
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */

public interface Command {

    /**
     * Проверка входящей комманды
     * @param command
     */
    boolean checkCommand (String command);

    /**
     * Выполнение входящей комманды после проверки
     * @param command
     */
    void execute(String command);
}
