package Hotel.service;

import Hotel.entity.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationService {
    // RESERVE
    void reserve(String hotelName, String guestName, UUID clientId);

    // CANCEL RESERVATION
    void cancelReserve(String hotelName, String guestName, UUID clientId);

    // FIND current client reservations
    List<Reservation> findClientReservations(UUID clientId);

    // GET reservations + cancellations history
    List<String> getClientHistory(UUID clientId);

}
