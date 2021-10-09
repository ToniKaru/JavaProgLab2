package se.iths.javaprog.toni.lab2;

import se.iths.javaprog.toni.lab2.console.*;
import se.iths.javaprog.toni.lab2.filescsv.CsvReader;
import se.iths.javaprog.toni.lab2.products.Product;
import se.iths.javaprog.toni.lab2.products.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class StartProductManagement {

    public static void main(String[] args) throws IOException {
        String path = CsvReader.getDefaultPath().toString();
        CsvReader<Product> csvReader;
        Display mainConsole = new Display("--- Start meny ---",getMainMenuOptions());
        Display productConsole = new Display("--- Produkt alternativ ---",getProductOptions());
        int choice = 0;
        int subMenuChoice = 0;

        if ("BUY".equals(args[0].toUpperCase(Locale.ROOT))) {
            //start in buy mode
        }
        else if (!args[0].isEmpty())
             path = args[0];
        csvReader = new CsvReader<>(path);
//        System.out.println(csvReader.getCsvPath());
        ProductService productService =
            new ProductService(csvReader.getProductsFromFile(csvReader.getCsvPath()));
//        System.out.println(productService);

        productService.addMultipleProducts(productService.getListOf5Products());
//        System.out.println(productService);



        do{
            mainConsole.printMenu();
            choice = UserInput.getChoice();

            if (choice == 1){
                do {
                    
                    productConsole.printMenu();
                    subMenuChoice = UserInput.getChoice();
                }while (subMenuChoice != 0);
            }

            System.out.println(choice);

        }while (choice != 0);


//        csvReader.saveAllProductsToFile(csvReader.getCsvPath(), productService);

    }
    private static List<String> getMainMenuOptions() {
        return List.of(
            "Hantera produkterna",
            "Hantera kategorier",
            "Kassan");
    }

    private static List<String> getProductOptions(){
        return List.of(
            "Lagersaldo av produkterna i lager"
        );
    }
    record MenuForPrint (String menuName, List<String> options){}



}
