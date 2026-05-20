package ua.khpi.oop.lab12.model;

import java.util.Objects;

public class Product {
    private final String name;
    private final String category;
    private final double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " [" + category + "] - " + String.format("%.2f UAH", price);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Product product)) {
            return false;
        }

        return Double.compare(price, product.price) == 0
                && Objects.equals(name, product.name)
                && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price);
    }
}