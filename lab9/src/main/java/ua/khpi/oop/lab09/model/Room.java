package ua.khpi.oop.lab09.model;

public class Room implements Comparable<Room> {
    private int number;
    private String type;
    private double price;

    public Room(int number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Room other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return "Room " + number + ", type: " + type + ", price: " + price;
    }
}