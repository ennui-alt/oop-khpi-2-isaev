package ua.khpi.oop.lab14.demo;

import ua.khpi.oop.lab14.model.Invoice;
import ua.khpi.oop.lab14.model.Order;
import ua.khpi.oop.lab14.model.Product;
import ua.khpi.oop.lab14.service.StoreService;
import ua.khpi.oop.lab14.threads.InvoiceTask;
import ua.khpi.oop.lab14.threads.OrderProcessingThread;
import ua.khpi.oop.lab14.threads.ProductDescriptionTask;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product laptop = new Product(
                1,
                "Laptop Acer",
                "Ноутбук для навчання, програмування та повсякденної роботи",
                32999
        );

        Product mouse = new Product(
                2,
                "Wireless Mouse",
                "Бездротова миша з ергономічною формою для комфортної роботи",
                899
        );

        Product cable = new Product(
                3,
                "USB Cable",
                "Короткий опис",
                149
        );

        Product keyboard = new Product(
                4,
                "Keyboard",
                "Механічна клавіатура з підсвічуванням для роботи та ігор",
                2499
        );

        List<Product> products = List.of(laptop, mouse, cable, keyboard);

        Order firstOrder = new Order(
                101,
                "Данил Колбасенко",
                List.of(laptop, mouse)
        );

        Order secondOrder = new Order(
                102,
                "Даня Вареник",
                List.of(cable, keyboard)
        );

        Order thirdOrder = new Order(
                103,
                "Артем Котенко",
                List.of(laptop, keyboard, cable)
        );

        List<Order> orders = List.of(firstOrder, secondOrder, thirdOrder);

        StoreService storeService = new StoreService();

        OrderProcessingThread orderThread = new OrderProcessingThread(orders, storeService);

        ProductDescriptionTask descriptionTask = new ProductDescriptionTask(products, storeService);
        Thread descriptionThread = new Thread(descriptionTask, "DescriptionChecker");

        InvoiceTask invoiceTask = new InvoiceTask(orders, storeService);
        Thread invoiceThread = new Thread(invoiceTask, "InvoiceGenerator");

        System.out.println("Запуск роботи інтернет-магазину");
        System.out.println();

        orderThread.start();
        descriptionThread.start();
        invoiceThread.start();

        try {
            orderThread.join();
            descriptionThread.join();
            invoiceThread.join();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Головний потік перервано");
            return;
        }

        System.out.println();
        System.out.println("Усі потоки завершили роботу");

        System.out.println();
        System.out.println("Оброблені замовлення:");
        for (String result : orderThread.getProcessedOrders()) {
            System.out.println(result);
        }

        System.out.println();
        System.out.println("Товари з коротким описом:");
        if (descriptionTask.getProductsWithShortDescription().isEmpty()) {
            System.out.println("Проблемних описів немає");
        } else {
            for (Product product : descriptionTask.getProductsWithShortDescription()) {
                System.out.println(product.getName());
            }
        }

        System.out.println();
        System.out.println("Сформовані накладні:");
        for (Invoice invoice : invoiceTask.getInvoices()) {
            System.out.println(invoice);
        }
    }
}