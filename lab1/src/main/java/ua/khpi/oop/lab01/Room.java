package ua.khpi.oop.lab01;

import java.util.Objects;

public class Room {

    int number;
    double price;

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Кімната:\n  Номер: %d\n  Ціна: %.2f грн", number, price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, price);
    }
}