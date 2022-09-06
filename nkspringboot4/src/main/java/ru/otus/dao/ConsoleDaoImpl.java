package ru.otus.dao;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class ConsoleDaoImpl implements ConsoleDao {

    private Scanner in;

    /**
     * Метод для считывания ответов из консоли.
     */
    @Override
    public String readFromConsole(){
        if (in.hasNextLine()) {
            return in.nextLine();
        }
        else return "";
    }

    /**
     * Метод для печати в консоль.
     */
    @Override
    public void printIntoConsole(String line){
        System.out.println(line);
    }
}
