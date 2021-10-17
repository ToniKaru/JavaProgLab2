package se.iths.javaprog.toni.lab2.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static se.iths.javaprog.toni.lab2.Guard.Against.NullOrBlank;

public class Brand {

    private final String name;
    private static final Set<Brand> brands = new HashSet<>();

    public Brand(String brand) {
        NullOrBlank(brand);
        this.name = brand;
        brands.add(this);
    }

    public static Brand of(String name){
        return brands.stream()
            .filter(b -> b.getName().equals(name))
            .findAny()
            .orElse(new Brand(name));
    }

    public Set<Brand> getAll(){
        return new HashSet<>(brands);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Brand{" +
            "name='" + name + '\'' +
            '}';
    }
}
