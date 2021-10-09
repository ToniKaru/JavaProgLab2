package se.iths.javaprog.toni.lab2.console;

import se.iths.javaprog.toni.lab2.products.ProductService;

import java.util.ArrayList;
import java.util.List;

public class Display<T> {

    private static final String DASHES = "---";
    private static final String exitChoice = "0. Exit";
    private List<String> menuChoices = new ArrayList<>();
    private String title;


    public Display(List<String> menuChoices){
        this("", menuChoices);
    }

    public Display(String title, List<String> menuChoices) {
        this.title = title;
        this.menuChoices = menuChoices;
    }


    public void printMenu(){
        System.out.println(title);

        if (menuChoices == null)
            throw new IllegalArgumentException();

        int i = 1;
        for (String choice : menuChoices) {
            System.out.println(i + ". " + choice);
            i++;
        }
        System.out.println(exitChoice);
    }

    public void printAllProducts(ProductService products){
        System.out.println("Produkter i lager");
        products.getAll().stream()
            .forEach(System.out::println);
    }



}
