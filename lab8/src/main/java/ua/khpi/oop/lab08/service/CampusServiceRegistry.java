package ua.khpi.oop.lab08.service;

import ua.khpi.oop.lab08.exception.InvalidCampusServiceException;
import ua.khpi.oop.lab08.model.CampusService;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CampusServiceRegistry implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<CampusService> services = new ArrayList<>();

    public void addService(CampusService service) throws InvalidCampusServiceException {
        if (service == null) {
            throw new InvalidCampusServiceException("Service must not be null");
        }

        if (findById(service.getId()).isPresent()) {
            throw new InvalidCampusServiceException("Service with id " + service.getId() + " already exists");
        }

        for (CampusService current : services) {
            boolean sameDate = current.getDate().equals(service.getDate());
            boolean sameTime = current.getTime().equals(service.getTime());
            boolean sameStudent = current.getStudentName().equalsIgnoreCase(service.getStudentName());

            if (sameDate && sameTime && sameStudent) {
                throw new InvalidCampusServiceException(
                        "Student already has another campus service at this date and time"
                );
            }
        }

        services.add(service);
    }

    public List<CampusService> getServices() {
        return List.copyOf(services);
    }

    public Optional<CampusService> findById(String id) {
        for (CampusService service : services) {
            if (service.getId().equalsIgnoreCase(id)) {
                return Optional.of(service);
            }
        }

        return Optional.empty();
    }

    public List<CampusService> findByStudentName(String studentName) {
        List<CampusService> result = new ArrayList<>();

        for (CampusService service : services) {
            if (service.getStudentName().equalsIgnoreCase(studentName)) {
                result.add(service);
            }
        }

        return result;
    }

    public List<CampusService> findByDate(LocalDate date) {
        List<CampusService> result = new ArrayList<>();

        for (CampusService service : services) {
            if (service.getDate().equals(date)) {
                result.add(service);
            }
        }

        return result;
    }

    public int size() {
        return services.size();
    }
}