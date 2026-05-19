package ua.khpi.oop.lab14.threads;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab14.model.Invoice;
import ua.khpi.oop.lab14.model.Order;
import ua.khpi.oop.lab14.model.Product;
import ua.khpi.oop.lab14.service.StoreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTaskTest {
    @Test
    void shouldCreateInvoicesAfterRun() {
        Product keyboard = new Product(
                1,
                "Keyboard",
                "Механічна клавіатура з підсвічуванням для роботи та ігор",
                2499
        );

        Order order = new Order(
                101,
                "Артем Котенко",
                List.of(keyboard)
        );

        StoreService storeService = new StoreService();
        InvoiceTask task = new InvoiceTask(List.of(order), storeService);

        task.run();

        assertEquals(1, task.getInvoices().size());

        Invoice invoice = task.getInvoices().get(0);

        assertEquals(101, invoice.getOrderNumber());
        assertEquals("Артем Котенко", invoice.getCustomerName());
        assertEquals(2499, invoice.getTotalAmount());
        assertNotNull(invoice.getCreatedAt());
    }

    @Test
    void shouldCreateSeveralInvoices() {
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

        Order firstOrder = new Order(
                101,
                "Данил Колбасенко",
                List.of(laptop)
        );

        Order secondOrder = new Order(
                102,
                "Даня Вареник",
                List.of(mouse)
        );

        StoreService storeService = new StoreService();
        InvoiceTask task = new InvoiceTask(List.of(firstOrder, secondOrder), storeService);

        task.run();

        assertEquals(2, task.getInvoices().size());
        assertEquals(101, task.getInvoices().get(0).getOrderNumber());
        assertEquals(102, task.getInvoices().get(1).getOrderNumber());
    }
}