package ua.khpi.oop.lab14.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int number;
    private final String customerName;
    private final List<Product> products;

    public Order(int number, String customerName, List<Product> products) {
        if (number <= 0) {
            throw new IllegalArgumentException("Номер замовлення має бути додатним");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Ім'я покупця не може бути порожнім");
        }

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Замовлення має містити хоча б один товар");
        }

        this.number = number;
        this.customerName = customerName;
        this.products = new ArrayList<>(products);
    }

    public int getNumber() {
        return number;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public double getTotalAmount() {
        double total = 0;

        for (Product product : products) {
            total += product.getPrice();
        }

        return total;
    }

    @Override
    public String toString() {
        return "замовлення №" + number + " для " + customerName;
    }
}