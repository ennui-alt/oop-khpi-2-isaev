package ua.khpi.oop.lab01;

import java.util.Objects;

public class Hotel {

    String name;
    String city;

    public Hotel(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Готель:\n  Назва: %s\n  Місто: %s", name, city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}