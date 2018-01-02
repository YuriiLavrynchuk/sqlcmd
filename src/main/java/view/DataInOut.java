package view;

import controller.*;

/**
 * Интерфейс служит шаблоном для класса ввода/вывода
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */

public interface DataInOut {
    void outPut(String message);
    String inPut();
}
