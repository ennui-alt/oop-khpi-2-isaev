package ua.khpi.oop.lab01;

import java.util.Objects;

public class Guest {
    String name;
    String phone;

    public Guest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Гість:\n  Ім'я: %s\n  Телефон: %s", name, phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}