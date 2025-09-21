package Hotel.repository.repoImp;

import Hotel.entity.Reservation;
import Hotel.repository.ReservationRepository;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.Instant;

public class InMemoryReservationRepository implements ReservationRepository {
    private final List<Reservation> reservations = new ArrayList<>();
    private final List<String> history = new ArrayList<>();
    private final List<UUID> historyClientIds = new ArrayList<>(); // track which client did each operation
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
    private int historyCounter = 1;

    // add to history
    private void addToHistory(String hotelName, String operation, UUID clientId) {
        String timestamp = Instant.now()
                .atZone(ZoneId.systemDefault())
                .format(formatter);
        String historyEntry = operation + "#" + historyCounter + " " + hotelName + " " + timestamp;
        history.add(historyEntry);
        historyClientIds.add(clientId);
        historyCounter++;
    }

    // reserve a room
    @Override
    public List<Reservation> reserve(String hotelName, String guestName, UUID clientId) {
        Reservation reservation = new Reservation(hotelName, guestName, clientId);
        reservations.add(reservation);
        addToHistory(hotelName, "RESERVATION", clientId);
        return List.of(reservation);
    }

    // cancel reservation
    @Override
    public void cancelReserve(String hotelName, String guestName, UUID clientId) {
        boolean removed = reservations.removeIf(reservation ->
                reservation.getHotelName().equalsIgnoreCase(hotelName) &&
                        reservation.getGuestId().equals(clientId));
        if (removed) {
            addToHistory(hotelName, "CANCELLATION", clientId);
        }
    }

    // find current client reservations
    @Override
    public List<Reservation> findByUserId(UUID ClientId) {
        List<Reservation> clientReservations = new ArrayList<>();
        for (Reservation reservation : this.reservations) {
            if (reservation.getGuestId().equals(ClientId)) {
                clientReservations.add(reservation);
            }
        }
        return clientReservations;
    }

    // get reservations and cancellations history for current client
    @Override
    public List<String> getHistory(UUID clientId) {
        List<String> clientHistory = new ArrayList<>();
        for (int i = 0; i < history.size(); i++) {
            if (historyClientIds.get(i).equals(clientId)) {
                clientHistory.add(history.get(i));
            }
        }
        return clientHistory;
    }
}