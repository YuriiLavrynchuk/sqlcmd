package exeption;

/**
 * Created by Admin on 30.03.2017.
 */
public class InvalidException extends Exception {

//    private DBconnection dBconnection;

    public InvalidException(String message, Exception e) {
        super(message);
//        this.dBconnection = dBconnection;
        System.out.println("Output = " + message + " " + e.getMessage());
    }

}
