package ua.khpi.oop.lab09.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingUtils {

    public static <T extends Reservation> List<T> getConfirmed(List<T> reservations) {
        List<T> result = new ArrayList<>();

        if (reservations == null) {
            return result;
        }

        for (T reservation : reservations) {
            if (reservation.isConfirmed()) {
                result.add(reservation);
            }
        }

        return result;
    }

    public static <T extends Reservation> boolean isRoomBusy(
            List<T> reservations,
            Room room,
            LocalDate from,
            LocalDate to
    ) {
        if (reservations == null) {
            return false;
        }

        for (T reservation : reservations) {
            if (reservation.isConfirmed() && reservation.hasConflict(room, from, to)) {
                return true;
            }
        }

        return false;
    }

    public static <T extends Comparable<? super T>> T findSmallest(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        T smallest = list.get(0);

        for (T item : list) {
            if (item.compareTo(smallest) < 0) {
                smallest = item;
            }
        }

        return smallest;
    }
}