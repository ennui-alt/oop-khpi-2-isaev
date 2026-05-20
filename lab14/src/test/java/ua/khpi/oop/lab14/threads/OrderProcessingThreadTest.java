package ua.khpi.oop.lab14.threads;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab14.model.Order;
import ua.khpi.oop.lab14.model.Product;
import ua.khpi.oop.lab14.service.StoreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderProcessingThreadTest {
    @Test
    void shouldProcessOrdersAfterRun() {
        Product laptop = new Product(
                1,
                "Laptop Acer",
                "Ноутбук для навчання та програмування",
                32999
        );

        Product mouse = new Product(
                2,
                "Wireless Mouse",
                "Бездротова миша для комфортної роботи",
                899
        );

        Order order = new Order(
                101,
                "Данил Колбасенко",
                List.of(laptop, mouse)
        );

        StoreService storeService = new StoreService();
        OrderProcessingThread thread = new OrderProcessingThread(List.of(order), storeService);

        thread.run();

        assertEquals(1, thread.getProcessedOrders().size());
        assertTrue(thread.getProcessedOrders().get(0).contains("замовлення №101"));
        assertTrue(thread.getProcessedOrders().get(0).contains("33898"));
    }
}