package se.iths.javaprog.toni.lab2.console;

import java.util.Scanner;

public class UserInput {
    static Scanner scanner = new Scanner(System.in);
    static int choice = 0;

    public static int getChoice(){
        //guard against bad input
        choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }
}
