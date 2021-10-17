package se.iths.javaprog.toni.lab2.entities;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static se.iths.javaprog.toni.lab2.Guard.Against.*;

public class Product {
    private final UUID id;
    private final String name;
    private final Brand brand;
    private final BigDecimal price;
    private final Category category;

    public Product(UUID id, String name, Brand brand, BigDecimal price, Category category) {
        Null(id);
        NullOrBlank(name);
        Null(brand);
        Negative(price);
        Null(category);

        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(brand, product.brand) && Objects.equals(price, product.price) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, price, category);
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", brand='" + brand + '\'' +
            ", price=" + price +
            ", category=" + category +
            '}';
    }
}
