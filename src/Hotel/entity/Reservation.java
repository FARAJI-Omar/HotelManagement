package Hotel.entity;

import java.util.UUID;
import java.time.Instant;

public class Reservation {
    private UUID id;
    private int nights;
    private Instant timestamp;
    private UUID clientId;
    private UUID hotelId;

    public Reservation(UUID hotelId, UUID clientId, int nights) {
        this.id = UUID.randomUUID();
        this.hotelId = hotelId;
        this.clientId = clientId;
        this.nights = nights;
        this.timestamp = Instant.now();
    }

    // getters
    public UUID getId() { return id; }
    public UUID getHotelId() { return hotelId; }
    public UUID getClientId() { return clientId; }
    public int getNights() { return nights; }
    public Instant getTimestamp() { return timestamp; }

    // Setters
    public void setNights(int nights) { this.nights = nights; }

}
