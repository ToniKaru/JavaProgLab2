package se.iths.javaprog.toni.lab2.products;

import se.iths.javaprog.toni.lab2.category.Category;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {

    private List<Product> products;


    public ProductService() {
        products = new ArrayList<>();
    }

    public ProductService(List<Product> products){
        this.products = new ArrayList<>(products);
    }

    public void addMultipleProducts (List<Product> products){
        if (this.products == null)
            this.products = new ArrayList<>(products);
        addAtEnd(products);
    }

    private void addAtEnd(List<Product> products) {
        this.products.addAll(products);
    }

    public void addProduct (Product product){
        if (product == null)
            throw new IllegalArgumentException();
        products.add(product);
    }

    public List<Product> getAll(){
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> getFirstProduct(){
        return products.stream()
            .findFirst();

    }

    public void getStockCountByCategory(){
         Map<Object, Long> map = products.stream()
            .map(Product::category)
            .collect(Collectors.groupingBy(p -> p.category(), Collectors.counting()));
        System.out.println(map);
    }


    public List<String> getProductStrings (){
        return products.stream()
            .map(product -> product.getProductString())
            .toList();
    }



    public void saveToFile(List list){
        printNotImplemented("saveToFile");
    }



    public List<Product> getListOf5Products(){
        return List.of(
            new Product(1L, "Marabou", "Mörk choklad", new BigDecimal(30.95), new Category("OTHER") ),
            new Product(2L, "Arla", "Milk", new BigDecimal(15.00), new Category("DAIRY")),
            new Product(3L, "Ica", "Lax", new BigDecimal(86.00), new Category("MEAT" )),
            new Product(4L, "Ica", "Tomater", new BigDecimal(26.00), new Category("PRODUCE")),
            new Product(5L, "Dole", "Champinjoner", new BigDecimal(33.00), new Category("PRODUCE")));
    }


    static void printNotImplemented(String name){
        System.out.println("Method " + name + " not implemented yet.");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductService that = (ProductService) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return "ProductService{" +
            "products=" + products +
            '}';
    }
}
