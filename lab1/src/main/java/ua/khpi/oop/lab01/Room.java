package ua.khpi.oop.lab01;

import java.util.Objects;

public class Room {
    private int number;
    private double price;

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
    }

    public Room(int number) {
        this(number, 0.0);
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void changePrice(double newPrice) {
        this.price = newPrice;
    }

    public boolean isExpensive() {
        return price > 2000.0;
    }

    @Override
    public String toString() {
        return String.format("Room:\n  number: %d\n  price: %.2f UAH", number, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return number == room.number &&
                Double.compare(price, room.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, price);
    }
}