package se.iths.javaprog.toni.lab2.products;

import se.iths.javaprog.toni.lab2.category.Category;

import java.math.BigDecimal;

public record Product (long id, String brand, String name, BigDecimal price, Category category) {


    String idStr (){
        return String.valueOf(id);
    }

    String priceStr (){
        return String.valueOf(price);
    }

    String categoryStr (){
        return category.category();
    }
    public String getProductString (){

        String[] arr = {Long.toString(id),
            brand,
            name,
            priceStr(),
            categoryStr()};
        return String.join(",", arr);
    }
}
