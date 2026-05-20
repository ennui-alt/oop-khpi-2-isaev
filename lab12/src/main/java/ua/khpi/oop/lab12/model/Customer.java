package ua.khpi.oop.lab12.model;

import java.util.Objects;

public class Customer {
    private final String fullName;
    private final String email;
    private final String city;

    public Customer(String fullName, String email, String city) {
        this.fullName = fullName;
        this.email = email;
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return fullName + " (" + city + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Customer customer)) {
            return false;
        }

        return Objects.equals(fullName, customer.fullName)
                && Objects.equals(email, customer.email)
                && Objects.equals(city, customer.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email, city);
    }
}