package exeption;

/**
 * Created by Admin on 30.03.2017.
 */
public class InvalidException extends Exception {

    public InvalidException(String message, Exception e) {
        super(message);
        System.out.println(message + " " + e.getMessage());
    }

}
