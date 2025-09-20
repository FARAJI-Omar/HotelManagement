package Hotel.repository;

import java.util.UUID;

public interface ReservationRepository {

    void reserve(String hotelName, String guestName, UUID clientId);

    void cancelReserve(String hotelName, String guestName, UUID clientId);

}
