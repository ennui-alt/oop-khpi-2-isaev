package ua.khpi.oop.lab12.service;

import ua.khpi.oop.lab12.model.Customer;
import ua.khpi.oop.lab12.model.Order;
import ua.khpi.oop.lab12.model.OrderStatus;
import ua.khpi.oop.lab12.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderService {
    private final List<Order> orders;

    public OrderService(List<Order> orders) {
        this.orders = List.copyOf(orders);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orders.stream()
                .filter(order -> order.getStatus() == status)
                .toList();
    }

    public List<String> getAllProductNamesSorted() {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getName)
                .distinct()
                .sorted()
                .toList();
    }

    public List<Order> getOrdersSortedByTotalPriceDesc() {
        return orders.stream()
                .sorted(Comparator.comparingDouble(Order::getTotalPrice).reversed())
                .toList();
    }

    public double calculateIncomeFromCompletedOrders() {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.COMPLETED)
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    public Map<OrderStatus, Long> countOrdersByStatus() {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getStatus,
                        Collectors.counting()
                ));
    }

    public Map<OrderStatus, List<Order>> groupOrdersByStatus() {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
    }

    public List<Customer> getCustomersWithCompletedOrders() {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.COMPLETED)
                .map(Order::getCustomer)
                .distinct()
                .toList();
    }

    public DoubleSummaryStatistics getOrderPriceStatistics() {
        return orders.stream()
                .collect(Collectors.summarizingDouble(Order::getTotalPrice));
    }

    public String buildOrdersReport() {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getDate))
                .map(order -> "Order #" + order.getNumber()
                        + " | " + order.getCustomer().getFullName()
                        + " | " + order.getStatus().getTitle()
                        + " | " + String.format("%.2f UAH", order.getTotalPrice()))
                .collect(Collectors.joining("\n"));
    }

    public List<Order> findOrdersByStatusImperative(OrderStatus status) {
        List<Order> result = new ArrayList<>();

        for (Order order : orders) {
            if (order.getStatus() == status) {
                result.add(order);
            }
        }

        return result;
    }
}