package Hotel.ui;

import Hotel.entity.Hotel;
import Hotel.entity.User;
import Hotel.service.ReservationService;
import Hotel.service.HotelService;
import Hotel.utils.ConsoleHelper;

import java.util.List;
import java.util.UUID;

public class ReserveHotelUI {
    private static final ReservationService reservationService = ConsoleUI.reservationService;
    private static final HotelService hotelService = ConsoleUI.hotelService;

    public static void reserveHotelUi(){
        // Get all hotels for clients
        List<Hotel> hotels = hotelService.findAllHotels();

        if (hotels.isEmpty()) {
            System.out.println("No hotels available for reservation.");
            return;
        }

        HotelsListUI.displayHotels("Available Hotels for Reservation");
        System.out.println("0. Cancel");

        int choice;
        do {
            choice = ConsoleHelper.readInt("Select the hotel number to reserve (0 to cancel): ");
            if (choice == 0) {
                System.out.println("Reservation cancelled.");
                return;
            }
            if (choice < 1 || choice > hotels.size()) {
                System.out.println("Invalid choice. Please select a number between 1 and " + hotels.size() + ", or 0 to cancel.");
            }
            else {
                Hotel selectedHotel = hotels.get(choice - 1);
                String guestName = ConsoleUI.currentUser.getName();
                UUID clientId = ConsoleUI.currentUser.getId();
                reservationService.reserve(selectedHotel.getHotelName(), guestName, clientId);
                return;
            }
        } while (true);
    }
}
