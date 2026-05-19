package ua.khpi.oop.lab12.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {
    private final int number;
    private final Customer customer;
    private final List<Product> products;
    private final OrderStatus status;
    private final LocalDate date;

    public Order(int number, Customer customer, List<Product> products,
                 OrderStatus status, LocalDate date) {
        this.number = number;
        this.customer = customer;
        this.products = List.copyOf(products);
        this.status = status;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return "Order #" + number
                + ", customer: " + customer.getFullName()
                + ", status: " + status.getTitle()
                + ", total: " + String.format("%.2f UAH", getTotalPrice());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Order order)) {
            return false;
        }

        return number == order.number
                && Objects.equals(customer, order.customer)
                && Objects.equals(products, order.products)
                && status == order.status
                && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, customer, products, status, date);
    }
}