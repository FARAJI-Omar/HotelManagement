package Hotel.entity;

import java.util.UUID;
import java.time.Instant;

public class Reservation {
    private UUID reservationId;
    private Instant timestamp;
    private String guestName;
    private String hotelName;
    private UUID guestId;

    public Reservation(String hotelName, String guestName, UUID guestId) {
        this.guestId = guestId;
        this.reservationId = UUID.randomUUID();
        this.hotelName = hotelName;
        this.guestName = guestName;
        this.timestamp = Instant.now();
    }

    // getters
    public UUID getId() { return reservationId; }
    public String getHotelName() { return hotelName; }
    public UUID getGuestId() { return guestId; }
    public Instant getTimestamp() { return timestamp; }
    public String getGuestName() { return guestName; }

}
