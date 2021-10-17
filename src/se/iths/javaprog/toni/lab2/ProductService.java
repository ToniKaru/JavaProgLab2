package se.iths.javaprog.toni.lab2;

import se.iths.javaprog.toni.lab2.entities.Product;

import java.util.*;

import static se.iths.javaprog.toni.lab2.Guard.Against.Null;

public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final Map<Product,Long> inventory = new HashMap<>();

    public ProductService() {

    }

    public void addProduct (Product product){
        Null(product);
        products.add(product);
    }

    public void addAll(Collection<Product> products) {
        this.products.addAll(products);
    }

    public void deleteProduct(Product product){
        products.remove(product);
    }

    public void deleteProduct(UUID id){
        products.stream()
            .filter(p-> p.getId().equals(id))
            .findFirst()
            .ifPresent(products::remove);
    }

    public Optional<Product> getProductById(UUID id){
        return products.stream()
            .filter(p-> p.getId().compareTo(id) == 0)
            .findFirst();
    }

    public List<Product> getProductsByName(String name){
        return products.stream()
            .filter(p-> p.getName().equals(name))
            .toList();
    }

    public List<Product> getAll(){
        return Collections.unmodifiableList(products);
    }

    public List<Product> getByCategory(String category){
        return products.stream()
            .filter(p -> p.getCategory().getName().equals(category))
            .toList();
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
