package ua.khpi.oop.lab14.service;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab14.model.Invoice;
import ua.khpi.oop.lab14.model.Order;
import ua.khpi.oop.lab14.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreServiceTest {
    @Test
    void shouldProcessOrder() {
        Product product = new Product(
                1,
                "Laptop Acer",
                "Ноутбук для навчання та програмування",
                32999
        );

        Order order = new Order(
                101,
                "Данил Колбасенко",
                List.of(product)
        );

        StoreService storeService = new StoreService();

        String result = storeService.processOrder(order);

        assertTrue(result.contains("замовлення №101"));
        assertTrue(result.contains("оброблено"));
        assertTrue(result.contains("32999"));
    }

    @Test
    void shouldCheckGoodDescription() {
        Product product = new Product(
                2,
                "Wireless Mouse",
                "Бездротова миша з ергономічною формою для комфортної роботи",
                899
        );

        StoreService storeService = new StoreService();

        assertTrue(storeService.isDescriptionGood(product));
    }

    @Test
    void shouldCheckShortDescription() {
        Product product = new Product(
                3,
                "USB Cable",
                "Короткий опис",
                149
        );

        StoreService storeService = new StoreService();

        assertFalse(storeService.isDescriptionGood(product));
    }

    @Test
    void shouldCreateInvoice() {
        Product product = new Product(
                4,
                "Keyboard",
                "Механічна клавіатура з підсвічуванням для роботи та ігор",
                2499
        );

        Order order = new Order(
                102,
                "Артем Котенко",
                List.of(product)
        );

        StoreService storeService = new StoreService();

        Invoice invoice = storeService.createInvoice(order);

        assertEquals(102, invoice.getOrderNumber());
        assertEquals("Артем Котенко", invoice.getCustomerName());
        assertEquals(2499, invoice.getTotalAmount());
        assertNotNull(invoice.getCreatedAt());
    }
}