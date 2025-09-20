package Hotel.service;

import java.util.UUID;

public interface ReservationService {
    // RESERVE
    void reserve(String hotelName, String guestName, UUID clientId);

    // CANCEL RESERVATION
    void cancelReserve(String hotelName, String guestName, UUID clientId);

}
