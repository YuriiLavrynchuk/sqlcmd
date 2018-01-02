package view;

/**
 * Класс ввода/вывода текстовых сообщений.
 * Предназначен для считывания и вывода текста.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import java.util.Scanner;

public class Console implements DataInOut {

    @Override
    public void outPut(String message){
        System.out.println(message);
    }

    /**
     * Метод считывает набор с клавиатуры
     * @return
     */
    @Override
    public String inPut(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
