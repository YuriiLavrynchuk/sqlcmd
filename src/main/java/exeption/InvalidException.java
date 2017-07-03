package exeption;

public class InvalidException extends Exception {

    public InvalidException(String message, Exception exception) {

        String eMessage = exception.getMessage();
        Throwable cause = exception.getCause();
        if (cause != null) {
            eMessage += " " +cause.getCause();
        }
        System.out.println("FAIL! Cause:\r\n" + eMessage + "\r\n" + message);
        System.out.println("Try again.");
    }
}
