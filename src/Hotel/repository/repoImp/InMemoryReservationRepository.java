package Hotel.repository.repoImp;

import Hotel.entity.Reservation;
import Hotel.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryReservationRepository implements ReservationRepository {
    private final List<Reservation> reservations  = new ArrayList<>();
    // reserve a room
    @Override
    public void reserve(String hotelName, String guestName, UUID clientId) {
        Reservation reservation = new Reservation(hotelName, guestName, clientId);
        reservations.add(reservation);
    }

    // cancel reservation
    @Override
    public void cancelReserve(String hotelName, String guestName, java.util.UUID clientId) {
        reservations.removeIf(reservation ->
                reservation.getHotelName().equalsIgnoreCase(hotelName) &&
                        reservation.getGuestId().equals(clientId));
    }
}
