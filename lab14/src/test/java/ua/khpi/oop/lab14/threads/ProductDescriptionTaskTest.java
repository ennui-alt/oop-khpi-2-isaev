package ua.khpi.oop.lab14.threads;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab14.model.Product;
import ua.khpi.oop.lab14.service.StoreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDescriptionTaskTest {
    @Test
    void shouldFindProductWithShortDescription() {
        Product laptop = new Product(
                1,
                "Laptop Acer",
                "Ноутбук для навчання та програмування",
                32999
        );

        Product cable = new Product(
                2,
                "USB Cable",
                "Короткий опис",
                149
        );

        StoreService storeService = new StoreService();
        ProductDescriptionTask task = new ProductDescriptionTask(List.of(laptop, cable), storeService);

        task.run();

        assertEquals(1, task.getProductsWithShortDescription().size());
        assertEquals("USB Cable", task.getProductsWithShortDescription().get(0).getName());
    }

    @Test
    void shouldNotFindShortDescriptionWhenAllProductsAreCorrect() {
        Product mouse = new Product(
                1,
                "Wireless Mouse",
                "Бездротова миша з ергономічною формою для комфортної роботи",
                899
        );

        Product keyboard = new Product(
                2,
                "Keyboard",
                "Механічна клавіатура з підсвічуванням для роботи та ігор",
                2499
        );

        StoreService storeService = new StoreService();
        ProductDescriptionTask task = new ProductDescriptionTask(List.of(mouse, keyboard), storeService);

        task.run();

        assertTrue(task.getProductsWithShortDescription().isEmpty());
    }
}