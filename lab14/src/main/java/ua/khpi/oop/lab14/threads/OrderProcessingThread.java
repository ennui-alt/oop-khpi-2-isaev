package ua.khpi.oop.lab14.threads;

import ua.khpi.oop.lab14.model.Order;
import ua.khpi.oop.lab14.service.StoreService;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessingThread extends Thread {
    private final List<Order> orders;
    private final StoreService storeService;
    private final List<String> processedOrders = new ArrayList<>();

    public OrderProcessingThread(List<Order> orders, StoreService storeService) {
        this.orders = orders;
        this.storeService = storeService;
        setName("OrderProcessor");
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        for (Order order : orders) {
            try {
                System.out.println("[" + threadName + "] починаю обробку: " + order);
                Thread.sleep(500);

                String result = storeService.processOrder(order);
                processedOrders.add(result);

                System.out.println("[" + threadName + "] " + result);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("[" + threadName + "] потік перервано");
                return;
            }
        }
    }

    public List<String> getProcessedOrders() {
        return processedOrders;
    }
}