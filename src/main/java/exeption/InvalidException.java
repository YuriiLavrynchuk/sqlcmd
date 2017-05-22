package exeption;

public class InvalidException extends Exception {

    public InvalidException(String message, Exception e) {
        super(message);
        System.out.println(message + " " + e.getMessage());
    }

}
