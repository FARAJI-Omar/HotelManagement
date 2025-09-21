package Hotel.ui;

import Hotel.entity.Hotel;
import Hotel.entity.Reservation;
import Hotel.service.HotelService;
import Hotel.service.ReservationService;
import Hotel.utils.ConsoleHelper;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class ClientReservationsUI {
    private static final ReservationService reservationService = ConsoleUI.reservationService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static void clientReservationsUI() {
        UUID ownerId = ConsoleUI.currentUser.getId();
        List<Reservation> reservations = reservationService.findClientReservations(ownerId);

        if (reservations.isEmpty()) {
            System.out.println("You have no reservations.");
        } else {
            System.out.println("Your reservations: \n");
            int order = 1;
            for (Reservation reservation : reservations) {
                System.out.println("RESERVATION #" + order);
                System.out.println("Hotel: " + reservation.getHotelName());
                String formattedDate = reservation.getTimestamp()
                        .atZone(ZoneId.systemDefault())
                        .format(formatter);
                System.out.println("Reserved at: " + formattedDate);
                System.out.println("----------------------------------------\n");
                order++;
            }
        }
    }
}
