package se.iths.javaprog.toni.lab2;

import se.iths.javaprog.toni.lab2.console.Console;
import se.iths.javaprog.toni.lab2.console.UserInput;
import se.iths.javaprog.toni.lab2.entities.Category;
import se.iths.javaprog.toni.lab2.entities.Product;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class StartProductManagement {

    private final ProductService productService = new ProductService();
    private final Console console = new Console("Start meny",getMainMenuOptions());
    private static final CsvFileService fileService = new CsvFileService();
    private static String fileName;


    public static void main(String[] args) {
        StartProductManagement productManagement = new StartProductManagement();
        if (args.length != 0)
            setFileName(args[0]);
        productManagement.run();
    }

    private static void setFileName(String arg) {
        if (!arg.isBlank())
            fileService.setFilePath(arg);
    }

    private void run() {
        loadFromFile();
        String choice;
        do {
            console.printMenu();
            choice = UserInput.getChoice();
        }while (executeChoice(choice));
        save(productService);
    }

    private void loadFromFile() {
        productService.addAll(fileService.getProductsFromFile(fileService.getPath()));
    }

    private void save(ProductService productService) {
        CsvFileService.saveToFile(fileService.getPath(), productService);
    }

    private boolean executeChoice(String choice) {
        boolean continueApp = true;
        switch (choice){
            case "1" -> printAllProducts(productService.getAll());
            case "2" -> createProduct();
            case "3" -> removeProduct();
            case "4" -> findProducts();
            case "5" -> printProductsByCategory();
            case "0" -> continueApp = false;
            default -> System.out.println("Ogiltig val. Prova igen...");
        }
        return continueApp;
    }

    private void printProductsByCategory() {

        printAllCategories(Category.getAll());
        System.out.println("Vilken kategori?");
        String category = UserInput.getLine();
        console.printProducts(productService.getByCategory(category));
    }

    private void printAllCategories(Set<Category> categories) {
        console.printCategories("Alla kategorier",categories);
    }

    private void printAllProducts(List<Product> all) {
        console.printProducts(all);
    }

    private List<String> getMainMenuOptions() {
        return List.of(
            "1. Visa alla produkterna i lager",
            "2. Lägg till en produkt",
            "3. Ta bort en produkt",
            "4. Hitta produkter",
            "5. Visa alla produkter i en kategori");
    }

    private void createProduct() {
        try {
            String line = getNewProductString();
            Product newProduct = fileService.createProduct(line);
            productService.addProduct(newProduct);
        } catch (Exception e){
            System.out.println("Det gick inte att skapa produkten");
        }
    }

    private String getNewProductString() {
        System.out.println("Produktens namn:");
        String name = UserInput.getLine();

        System.out.println("Märke:");
        String brand = UserInput.getLine();

        System.out.println("Pris:");
        String price = UserInput.getLine();

        System.out.println("Kategori:");
        String category = UserInput.getLine();

        return String.join(",",UUID.randomUUID().toString(),name,brand, price, category);
    }

    private void removeProduct() {
        printAllProducts(productService.getAll());
        System.out.println("Vilken produkt vill du ta bort? (id)");
        UUID identifier = UUID.fromString(UserInput.getLine());
        productService.deleteProduct(identifier);
    }

    private void findProducts() {
        System.out.println("Vilken produkt vill du hitta? (namn)");
        String name = UserInput.getLine();
        System.out.println("Produkter som matchar: ");
        printAllProducts(productService.getProductsByName(name));
    }
}
