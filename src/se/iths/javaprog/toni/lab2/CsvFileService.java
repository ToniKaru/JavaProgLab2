package se.iths.javaprog.toni.lab2;

import se.iths.javaprog.toni.lab2.entities.Brand;
import se.iths.javaprog.toni.lab2.entities.Category;
import se.iths.javaprog.toni.lab2.entities.Product;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvFileService {

    private static final Pattern pattern = Pattern.compile(",");
    private static final String defaultPath = "toniProducts.csv";
    private static final String homePath = System.getProperty("user.home");
    private String filePath;
    private Path path;

    public CsvFileService(){
        filePath = defaultPath;
        path = Path.of(homePath,filePath);
    }

    public Path getPath() {
        return path;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Collection<Product> getProductsFromFile(Path csvPath) {
        if (!Files.exists(csvPath)){
            System.out.println("file not found");
            return List.of();
        }
        Collection<Product> products = new ArrayList<>();
        try (Stream<String> lines = Files.lines(csvPath)) {
            products = lines
                .skip(1)
                .map(CsvFileService::createProduct)
                .collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println("Det gick inte att ladda från filen.");
        }
        return products;
    }

    static Product createProduct(String line) {
        String[] arr = pattern.split(line);
        Product product;

        UUID id = UUID.fromString(arr[0]);
        String name = arr[1];
        Brand brand = new Brand(arr[2]);
        BigDecimal price = new BigDecimal(arr[3]);
        Category category = Category.of(arr[4]);

        product = new Product(id, name, brand, price, category);
        return product;
    }

     static void saveToFile(Path path, ProductService products) {
        File file = new File(String.valueOf(path));
        List<String> strings = new ArrayList<>();
        strings.add("Uppdaterad produkter");
        products.getAll().forEach(product -> csvRow(product,strings));
        try {
            Files.write(path, strings);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void csvRow(Product product, List<String> strings) {
        strings.add(String.join(pattern.toString(), product.getId().toString(),
            product.getName(),
            product.getBrand().getName(),
            product.getPrice().toPlainString(),
            product.getCategory().getName()));
    }

}

