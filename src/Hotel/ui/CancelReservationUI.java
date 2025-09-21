package Hotel.ui;

import Hotel.entity.Reservation;
import Hotel.service.HotelService;
import Hotel.service.ReservationService;
import Hotel.utils.ConsoleHelper;

import java.util.List;
import java.util.UUID;

public class CancelReservationUI {
    private static final ReservationService reservationService = ConsoleUI.reservationService;
    private static final HotelService hotelService = ConsoleUI.hotelService;

    public static void cancelReservationUI() {
        UUID clientId = ConsoleUI.currentUser.getId();
        List<Reservation> reservations = reservationService.findClientReservations(clientId);

        if (reservations.isEmpty()) {
            System.out.println("You have no reservations to cancel.");
            return;
        }

        // display reservations
        ClientReservationsUI.clientReservationsUI();

        System.out.println("0. Cancel");

        // get user choice
        int choice = ConsoleHelper.readInt("Select the reservation number to cancel (0 to cancel)");

        if (choice == 0) {
            System.out.println("Operation cancelled.");
            return;
        }

        if (choice < 1 || choice > reservations.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        // get selected reservation
        Reservation selectedReservation = reservations.get(choice - 1);

        // cancel the reservation
        reservationService.cancelReserve(selectedReservation.getHotelName(), selectedReservation.getGuestName(), clientId);
        System.out.println("Reservation #" + choice + " cancelled successfully.");
    }
}
