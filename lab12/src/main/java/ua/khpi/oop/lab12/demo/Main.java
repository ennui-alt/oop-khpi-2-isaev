package ua.khpi.oop.lab12.demo;

import ua.khpi.oop.lab12.model.Customer;
import ua.khpi.oop.lab12.model.Order;
import ua.khpi.oop.lab12.model.OrderStatus;
import ua.khpi.oop.lab12.model.Product;
import ua.khpi.oop.lab12.service.OrderService;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Product laptop = new Product("Lenovo IdeaPad Laptop", "Electronics", 28999.00);
        Product mouse = new Product("Logitech Mouse", "Accessories", 899.00);
        Product keyboard = new Product("HyperX Keyboard", "Accessories", 2499.00);
        Product monitor = new Product("Samsung 24 Monitor", "Electronics", 5799.00);
        Product headphones = new Product("JBL Headphones", "Audio", 1599.00);
        Product powerBank = new Product("Xiaomi Power Bank", "Accessories", 1299.00);

        Customer customer1 = new Customer("Zakhar Isaiev", "zakhar@example.com", "Kharkiv");
        Customer customer2 = new Customer("Oleg Vinnik", "oleg@example.com", "Kyiv");
        Customer customer3 = new Customer("Valentina Budeiko", "valentina@example.com", "Lviv");

        List<Order> orders = List.of(
                new Order(101, customer1, List.of(laptop, mouse),
                        OrderStatus.COMPLETED, LocalDate.of(2026, 5, 1)),

                new Order(102, customer2, List.of(keyboard, headphones),
                        OrderStatus.PAID, LocalDate.of(2026, 5, 3)),

                new Order(103, customer3, List.of(monitor, powerBank),
                        OrderStatus.SHIPPED, LocalDate.of(2026, 5, 5)),

                new Order(104, customer1, List.of(mouse, keyboard, headphones),
                        OrderStatus.COMPLETED, LocalDate.of(2026, 5, 7)),

                new Order(105, customer2, List.of(powerBank),
                        OrderStatus.CANCELLED, LocalDate.of(2026, 5, 8)),

                new Order(106, customer3, List.of(laptop, monitor),
                        OrderStatus.NEW, LocalDate.of(2026, 5, 10))
        );

        OrderService service = new OrderService(orders);

        System.out.println("=== Lab 12: Stream API / Online Store ===");

        System.out.println("\n1. All orders");
        System.out.println("Total orders: " + service.getAllOrders().size());

        System.out.println("\n2. Completed orders: filter()");
        List<Order> completedOrders = service.findOrdersByStatus(OrderStatus.COMPLETED);
        System.out.println("Completed orders: " + formatOrderNumbers(completedOrders));

        System.out.println("\n3. Product names: flatMap() + map() + distinct() + sorted()");
        System.out.println(String.join(", ", service.getAllProductNamesSorted()));

        System.out.println("\n4. Orders sorted by total price: sorted()");
        List<Order> sortedOrders = service.getOrdersSortedByTotalPriceDesc();
        Order mostExpensiveOrder = sortedOrders.get(0);
        System.out.println("Most expensive order: Order #" + mostExpensiveOrder.getNumber());
        System.out.printf("Total price: %.2f UAH%n", mostExpensiveOrder.getTotalPrice());

        System.out.println("\n5. Income from completed orders: filter() + sum()");
        System.out.printf("Completed orders income: %.2f UAH%n",
                service.calculateIncomeFromCompletedOrders());

        System.out.println("\n6. Order count by status: groupingBy() + counting()");
        Map<OrderStatus, Long> statusCount = service.countOrdersByStatus();
        statusCount.forEach((status, count) ->
                System.out.println(status.getTitle() + ": " + count));

        System.out.println("\n7. Customers with completed orders");
        service.getCustomersWithCompletedOrders()
                .forEach(customer -> System.out.println(customer.getFullName()));

        System.out.println("\n8. Order price statistics: summarizingDouble()");
        DoubleSummaryStatistics statistics = service.getOrderPriceStatistics();
        System.out.printf("Min: %.2f UAH, Max: %.2f UAH, Average: %.2f UAH, Total: %.2f UAH%n",
                statistics.getMin(),
                statistics.getMax(),
                statistics.getAverage(),
                statistics.getSum());

        System.out.println("\n9. Short text report: joining()");
        System.out.println(service.buildOrdersReport()
                .lines()
                .limit(3)
                .collect(Collectors.joining("\n")));
        System.out.println("...");

        System.out.println("\n10. Imperative loop vs Stream API");
        System.out.println("Loop result:   " + formatOrderNumbers(
                service.findOrdersByStatusImperative(OrderStatus.COMPLETED)));

        System.out.println("Stream result: " + formatOrderNumbers(
                service.findOrdersByStatus(OrderStatus.COMPLETED)));
    }

    private static String formatOrderNumbers(List<Order> orders) {
        return orders.stream()
                .map(order -> "#" + order.getNumber())
                .collect(Collectors.joining(", "));
    }
}