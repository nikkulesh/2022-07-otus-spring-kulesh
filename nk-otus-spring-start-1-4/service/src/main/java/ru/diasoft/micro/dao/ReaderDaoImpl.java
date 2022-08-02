package ru.diasoft.micro.dao;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class ReaderDaoImpl implements ReaderDao{

    private Scanner in;

    /**
     * Метод для считывания ответов из консоли.
     */
    @Override
    public String readFromConsole(String question){
        if (in.hasNextLine()) {
            return in.nextLine();
        }
        else return "";
    }
}
