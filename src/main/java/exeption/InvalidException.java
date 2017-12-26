package exeption;


/**
 * Класс который отвечает за вывод информации об ошибках.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
public class InvalidException extends Exception {

    /**
     * Создаёт объект ошибки InvalidException
     * @param message сообщение из места возникновения ошибки
     * @param exception перехваченная ошибка
     */
    public InvalidException(String message, Exception exception){

        String eMessage = exception.getMessage();
        Throwable cause = exception.getCause();
        if (cause != null){
            eMessage += " " +cause.getCause();
        }
        System.out.println("FAIL! Cause:\r\n" + eMessage + "\r\n" + message +"\r\n" + "Try again.");
    }
}
