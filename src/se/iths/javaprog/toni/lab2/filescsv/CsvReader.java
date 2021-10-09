package se.iths.javaprog.toni.lab2.filescsv;

import se.iths.javaprog.toni.lab2.category.Category;
import se.iths.javaprog.toni.lab2.products.Product;
import se.iths.javaprog.toni.lab2.products.ProductService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReader<T> {

    private static Pattern pattern = Pattern.compile(",");
    static final Path defaultPath = Path.of("resources","defaultProducts.csv");
    private static String homePath = System.getProperty("user.home");
    String directory;
    String fileName;
    String filePath;
    private Path csvPath;


    public CsvReader (String filePath){
        this.filePath = filePath;
        csvPath = Path.of(homePath, filePath);
    }

    public CsvReader (String directory, String fileName){
        this.directory = directory;
        this.fileName = fileName;
        csvPath = Path.of(homePath, directory, fileName);
    }

    public String getDirectory() {
        return directory;
    }

    public static Path getDefaultPath() {
        return defaultPath;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Path getCsvPath (){
        return csvPath;
    }


    public List<Product> getProductsFromFile(Path csvPath) {
        List<Product> products = List.of();
        try (Stream<String> lines = Files.lines(csvPath)) {
            products = lines
                .map(CsvReader::createProduct)
                .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private static Product createProduct(String line) {
        String[] split = pattern.split(line);
        return new Product(Long.parseLong(split[0]), split[1], split[2], new BigDecimal(split[3]), new Category(split[4]));
    }

    public void saveAllProductsToFile(Path csvPath, ProductService products) throws IOException {
        File csvOutputFile = new File(String.valueOf(csvPath));
        if(!csvOutputFile.exists())
            //skapa filen
        Files.write(csvPath, products.getProductStrings());
    }

}

