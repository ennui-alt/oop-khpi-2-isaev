package ua.khpi.oop.lab14.threads;

import ua.khpi.oop.lab14.model.Invoice;
import ua.khpi.oop.lab14.model.Order;
import ua.khpi.oop.lab14.service.StoreService;

import java.util.ArrayList;
import java.util.List;

public class InvoiceTask implements Runnable {
    private final List<Order> orders;
    private final StoreService storeService;
    private final List<Invoice> invoices = new ArrayList<>();

    public InvoiceTask(List<Order> orders, StoreService storeService) {
        this.orders = orders;
        this.storeService = storeService;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        for (Order order : orders) {
            try {
                System.out.println("[" + threadName + "] формую накладну для замовлення №" + order.getNumber());
                Thread.sleep(600);

                Invoice invoice = storeService.createInvoice(order);
                invoices.add(invoice);

                System.out.println("[" + threadName + "] накладна готова для замовлення №" + order.getNumber());
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("[" + threadName + "] потік перервано");
                return;
            }
        }
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}