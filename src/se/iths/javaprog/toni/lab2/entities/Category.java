package se.iths.javaprog.toni.lab2.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static se.iths.javaprog.toni.lab2.Guard.Against.NullOrBlank;

public class Category {

    private final String name;
    private static final Set<Category> categories = new HashSet<>();

    private Category(String category) {
        NullOrBlank(category);
        this.name = category;
        categories.add(this);
    }

    public static Category of(String name){
        return categories.stream()
            .filter(c -> c.getName().equals(name))
            .findAny()
            .orElse(new Category(name));
    }

    public static Set<Category> getAll(){
        return new HashSet<>(categories);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Category{" +
            "name='" + name + '\'' +
            '}';
    }
}
