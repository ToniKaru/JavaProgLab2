package se.iths.javaprog.toni.lab2.category;

public record Category (String category) {

    @Override
    public String toString() {
        return
            "category='" + category + '\'' +
            '}';
    }
}
