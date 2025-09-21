package Hotel.repository;

import Hotel.entity.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository {

    // CREATE
    List<Reservation> reserve(String hotelName, String guestName, UUID clientId);

    // DELETE
    void cancelReserve(String hotelName, String guestName, UUID clientId);

    // READ
    List<Reservation> findByUserId(UUID ClientId);

    // History
    List<String> getHistory(UUID clientId);

}


