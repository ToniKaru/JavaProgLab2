package se.iths.javaprog.toni.lab2.console;

import se.iths.javaprog.toni.lab2.entities.Category;
import se.iths.javaprog.toni.lab2.entities.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Console<T> {

    private static final String DASHES = "---";
    private static final String exitChoice = "0. Exit";
    private static final Locale swedish = new Locale("sv", "SE");
    private static final NumberFormat swedishCurrency = NumberFormat.getCurrencyInstance(swedish);
    private List<String> menuChoices;
    private String title;


    public Console(String title, List<String> menuChoices) {
        this.title = title;
        this.menuChoices = menuChoices;
    }

    public void printMenu(){
        printTitle(title);
        if (menuChoices == null)
            throw new IllegalArgumentException();
        printChoices();
        System.out.println(exitChoice);
    }

    private void printChoices() {
        menuChoices.forEach(System.out::println);
    }

    private static void printTitle(String title) {
        System.out.println(DASHES + title + DASHES);
    }

    public void printProduct(Product product){
        printProducts(List.of(product));
    }

    public void printProducts(List<Product> all) {
        System.out.printf("%-25s%-22s%-18s%-20s%-40s%n",
            "Name","Brand","Price","Category","UUID");
        all.forEach(Console::printProductRow);
        System.out.println();
    }

    private static void printProductRow(Product product) {
        System.out.printf("%-25s%-22s%-18s%-20s%-40s%n",
            product.getName(),
            product.getBrand().getName(),
            swedishCurrency.format(product.getPrice()),
            product.getCategory().getName(),
            product.getId().toString());
    }

    public void printCategories(String title, Set<Category> categories) {
        printTitle(title);
        categories.forEach(category -> System.out.println(category.getName()));
        System.out.println();
    }
}
