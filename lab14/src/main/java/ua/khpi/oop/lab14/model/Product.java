package ua.khpi.oop.lab14.model;

public class Product {
    private final int id;
    private final String name;
    private final String description;
    private final double price;

    public Product(int id, String name, String description, double price) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id товару має бути додатним");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Опис товару не може бути порожнім");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Ціна товару має бути додатною");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean hasNormalDescription() {
        return description.trim().length() >= 20;
    }

    @Override
    public String toString() {
        return name + " — " + price + " грн";
    }
}