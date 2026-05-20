package ua.khpi.oop.lab12.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab12.model.Customer;
import ua.khpi.oop.lab12.model.Order;
import ua.khpi.oop.lab12.model.OrderStatus;
import ua.khpi.oop.lab12.model.Product;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private OrderService service;
    private Customer alice;

    private Order completedOrder;
    private Order paidOrder;
    private Order secondCompletedOrder;
    private Order cancelledOrder;

    @BeforeEach
    void setUp() {
        Product phone = new Product("Phone", "Electronics", 1000.00);
        Product phoneCase = new Product("Phone Case", "Accessories", 50.00);
        Product laptop = new Product("Laptop", "Electronics", 2000.00);
        Product mouse = new Product("Mouse", "Accessories", 100.00);
        Product headphones = new Product("Headphones", "Audio", 300.00);

        alice = new Customer("Alice Brown", "alice@example.com", "London");
        Customer bob = new Customer("Bob Smith", "bob@example.com", "Manchester");

        completedOrder = new Order(
                1,
                alice,
                List.of(phone, phoneCase),
                OrderStatus.COMPLETED,
                LocalDate.of(2026, 1, 10)
        );

        paidOrder = new Order(
                2,
                bob,
                List.of(laptop, mouse),
                OrderStatus.PAID,
                LocalDate.of(2026, 1, 11)
        );

        secondCompletedOrder = new Order(
                3,
                alice,
                List.of(headphones, mouse),
                OrderStatus.COMPLETED,
                LocalDate.of(2026, 1, 12)
        );

        cancelledOrder = new Order(
                4,
                bob,
                List.of(phoneCase),
                OrderStatus.CANCELLED,
                LocalDate.of(2026, 1, 13)
        );

        service = new OrderService(List.of(
                completedOrder,
                paidOrder,
                secondCompletedOrder,
                cancelledOrder
        ));
    }

    @Test
    void shouldFilterOrdersByStatus() {
        List<Order> completedOrders = service.findOrdersByStatus(OrderStatus.COMPLETED);

        assertEquals(List.of(completedOrder, secondCompletedOrder), completedOrders);
    }

    @Test
    void shouldReturnEmptyListWhenStatusIsNotFound() {
        List<Order> result = service.findOrdersByStatus(OrderStatus.SHIPPED);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenServiceHasNoOrders() {
        OrderService emptyService = new OrderService(List.of());

        List<Order> result = emptyService.findOrdersByStatus(OrderStatus.COMPLETED);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldMapProductsToUniqueSortedNames() {
        List<String> names = service.getAllProductNamesSorted();

        assertEquals(List.of(
                "Headphones",
                "Laptop",
                "Mouse",
                "Phone",
                "Phone Case"
        ), names);
    }

    @Test
    void shouldSortOrdersByTotalPriceDescending() {
        List<Order> sortedOrders = service.getOrdersSortedByTotalPriceDesc();

        assertEquals(2, sortedOrders.get(0).getNumber());
        assertEquals(1, sortedOrders.get(1).getNumber());
        assertEquals(3, sortedOrders.get(2).getNumber());
        assertEquals(4, sortedOrders.get(3).getNumber());
    }

    @Test
    void shouldCalculateIncomeAndStatistics() {
        double income = service.calculateIncomeFromCompletedOrders();
        DoubleSummaryStatistics statistics = service.getOrderPriceStatistics();

        assertEquals(1450.00, income, 0.001);

        assertEquals(4, statistics.getCount());
        assertEquals(50.00, statistics.getMin(), 0.001);
        assertEquals(2100.00, statistics.getMax(), 0.001);
        assertEquals(3600.00, statistics.getSum(), 0.001);
        assertEquals(900.00, statistics.getAverage(), 0.001);
    }

    @Test
    void shouldGroupAndCountOrdersByStatus() {
        Map<OrderStatus, Long> countByStatus = service.countOrdersByStatus();
        Map<OrderStatus, List<Order>> groupedByStatus = service.groupOrdersByStatus();

        assertEquals(2L, countByStatus.get(OrderStatus.COMPLETED));
        assertEquals(1L, countByStatus.get(OrderStatus.PAID));
        assertEquals(1L, countByStatus.get(OrderStatus.CANCELLED));

        assertEquals(List.of(completedOrder, secondCompletedOrder),
                groupedByStatus.get(OrderStatus.COMPLETED));
    }

    @Test
    void shouldBuildReportAndReturnCustomersWithoutDuplicates() {
        String report = service.buildOrdersReport();
        List<Customer> customers = service.getCustomersWithCompletedOrders();

        assertTrue(report.contains("Order #1"));
        assertTrue(report.contains("Alice Brown"));
        assertTrue(report.contains("Completed"));
        assertTrue(report.contains("1050"));

        assertEquals(List.of(alice), customers);
    }

    @Test
    void streamAndImperativeMethodsShouldReturnSameResult() {
        List<Order> streamResult = service.findOrdersByStatus(OrderStatus.COMPLETED);
        List<Order> imperativeResult = service.findOrdersByStatusImperative(OrderStatus.COMPLETED);

        assertEquals(imperativeResult, streamResult);
    }
}