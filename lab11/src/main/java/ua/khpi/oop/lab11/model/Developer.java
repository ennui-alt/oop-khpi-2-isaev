package ua.khpi.oop.lab11.model;

import java.util.Objects;

public class Developer {

    private String name;
    private String email;
    private String specialization;

    public Developer(String name, String email, String specialization) {
        this.name = name;
        this.email = email;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return name + " [" + specialization + ", " + email + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer developer)) return false;
        return Objects.equals(email, developer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}