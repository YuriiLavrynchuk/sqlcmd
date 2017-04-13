package view;

import java.util.Scanner;

/**
 * Created by yuriy.lavrinchuk on 10.04.2017.
 */
public class Console implements DataInOut {
    @Override
    public void outPut(String message) {
        System.out.println(message);
    }

    @Override
    public String inPut() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
