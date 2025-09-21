package Hotel.ui;

import Hotel.entity.Reservation;
import Hotel.service.ReservationService;
import Hotel.utils.ConsoleHelper;

import java.util.List;
import java.util.UUID;

public class ClientSpace {
    private static final ReservationService reservationService = ConsoleUI.reservationService;

    public static void clientSpace() {
        UUID ownerId = ConsoleUI.currentUser.getId();
        while (true) {
            System.out.println("\n====== Client Space ======");
            System.out.println("1. Reserve Room");
            System.out.println("2. View Reservations");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservation History");
            System.out.println("5. Rate Hotel");
            System.out.println("6. Update Profile");
            System.out.println("7. Logout");

            int choice = ConsoleHelper.readInt("Choose an option");

            switch (choice) {
                case 1 -> {
                    ReserveHotelUI.reserveHotelUi();
                }
                case 2 -> {
                    ClientReservationsUI.clientReservationsUI();
                }
                case 3 -> {
                    CancelReservationUI.cancelReservationUI();                }
                case 4 -> {
                    List<String> history = reservationService.getClientHistory(ownerId);
                    System.out.println("\n===== Your Reservation History =====");
                    for (String operation : history) {
                        System.out.println(operation);
                    }
                    System.out.println("=====================================\n");
                }
                case 5 -> {
                    System.out.println("Rate Hotel");
                }
                case 6 -> {
                    System.out.println("Update Profile!");
                }
                case 7 -> {
                    System.out.println("Logging out...");
                    ConsoleUI.isHotelier = false;
                    ConsoleUI.start();
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}
