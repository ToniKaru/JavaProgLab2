package se.iths.javaprog.toni.lab2.console;

import java.util.Scanner;

import static se.iths.javaprog.toni.lab2.Guard.Against.*;

public class UserInput {
    static Scanner scanner = new Scanner(System.in);
    static String bufferedString = "";

    public static String getChoice(){
        return scanner.nextLine();
    }

    public static String getLine(){
        bufferedString = scanner.nextLine();
        NullOrBlank(bufferedString);
        return bufferedString;
    }
}
