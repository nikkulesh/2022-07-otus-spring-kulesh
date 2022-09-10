package ru.otus.nkspringboot5.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
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
