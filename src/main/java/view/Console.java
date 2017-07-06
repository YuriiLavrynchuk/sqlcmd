package view;

import java.util.Scanner;

public class Console implements DataInOut {
    @Override
    public void outPut(String message){
        System.out.println(message);
    }

    @Override
    public String inPut(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
