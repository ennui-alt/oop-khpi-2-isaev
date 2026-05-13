package ua.khpi.oop.lab04;

import java.util.Locale;
import java.util.Objects;

public class Room {
    private int number;
    private double price;

    public Room(int number, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Ціна номера не може бути від'ємною");
        }
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
        if (price < 0) {
            throw new IllegalArgumentException("Ціна номера не може бути від'ємною");
        }
        this.price = price;
    }

    public void changePrice(double newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("Ціна номера не може бути від'ємною");
        }
        this.price = newPrice;
    }

    public boolean isExpensive() {
        return price > 2000.0;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Room:\n  number: %d\n  price: %.2f UAH", number, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number &&
                Double.compare(room.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, price);
    }
}