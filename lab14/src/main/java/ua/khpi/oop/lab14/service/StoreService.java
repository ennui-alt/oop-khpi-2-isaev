package ua.khpi.oop.lab14.service;

import ua.khpi.oop.lab14.model.Invoice;
import ua.khpi.oop.lab14.model.Order;
import ua.khpi.oop.lab14.model.Product;

import java.time.LocalDateTime;

public class StoreService {
    public String processOrder(Order order) {
        return order + " оброблено. Сума до сплати: " + order.getTotalAmount() + " грн";
    }

    public boolean isDescriptionGood(Product product) {
        return product.hasNormalDescription();
    }

    public Invoice createInvoice(Order order) {
        return new Invoice(
                order.getNumber(),
                order.getCustomerName(),
                order.getTotalAmount(),
                LocalDateTime.now()
        );
    }
}