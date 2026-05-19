package ua.khpi.oop.lab09.model;

public class BookingLink<C, R> {
    private C customer;
    private R resource;

    public BookingLink(C customer, R resource) {
        this.customer = customer;
        this.resource = resource;
    }

    public C getCustomer() {
        return customer;
    }

    public R getResource() {
        return resource;
    }

    @Override
    public String toString() {
        return "Customer: " + customer + ", resource: " + resource;
    }
}