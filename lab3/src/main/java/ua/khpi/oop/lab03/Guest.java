package ua.khpi.oop.lab03;

import java.util.Objects;

public class Guest {
    private String name;
    private String phone;

    public Guest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Guest(String name) {
        this(name, "не вказано");
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void changePhone(String newPhone) {
        this.phone = newPhone;
    }

    @Override
    public String toString() {
        return String.format("Guest:\n  name: %s\n  phone: %s", name, phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(name, guest.name) &&
                Objects.equals(phone, guest.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}