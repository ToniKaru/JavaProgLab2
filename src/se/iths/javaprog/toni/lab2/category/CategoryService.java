package se.iths.javaprog.toni.lab2.category;

import java.util.HashSet;
import java.util.Set;

public class CategoryService {

    private Set<Category> categories;

    CategoryService (){
        categories = Set.of();
    }

    public void removeCategory (Category category){
        if (category == null)
            throw new IllegalArgumentException();
        categories.remove(category);
    }

    public void addCategory (Category category){
        if (category == null)
            throw new IllegalArgumentException();
        categories.add(category);
    }

    public Set<Category> getCategories(){
        return new HashSet<>(categories);
    }


}
