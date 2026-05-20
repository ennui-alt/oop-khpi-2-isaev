package ua.khpi.oop.lab14.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private final int orderNumber;
    private final String customerName;
    private final double totalAmount;
    private final LocalDateTime createdAt;

    public Invoice(int orderNumber, String customerName, double totalAmount, LocalDateTime createdAt) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return "накладна №" + orderNumber
                + ", покупець: " + customerName
                + ", сума: " + totalAmount + " грн"
                + ", створено: " + createdAt.format(formatter);
    }
}