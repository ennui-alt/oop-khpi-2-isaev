package ua.khpi.oop.lab14.threads;

import ua.khpi.oop.lab14.model.Product;
import ua.khpi.oop.lab14.service.StoreService;

import java.util.ArrayList;
import java.util.List;

public class ProductDescriptionTask implements Runnable {
    private final List<Product> products;
    private final StoreService storeService;
    private final List<Product> productsWithShortDescription = new ArrayList<>();

    public ProductDescriptionTask(List<Product> products, StoreService storeService) {
        this.products = products;
        this.storeService = storeService;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        for (Product product : products) {
            try {
                System.out.println("[" + threadName + "] перевіряю опис товару: " + product.getName());
                Thread.sleep(350);

                if (storeService.isDescriptionGood(product)) {
                    System.out.println("[" + threadName + "] опис нормальний: " + product.getName());
                } else {
                    productsWithShortDescription.add(product);
                    System.out.println("[" + threadName + "] опис занадто короткий: " + product.getName());
                }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("[" + threadName + "] потік перервано");
                return;
            }
        }
    }

    public List<Product> getProductsWithShortDescription() {
        return productsWithShortDescription;
    }
}