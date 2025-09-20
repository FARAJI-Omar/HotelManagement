package Hotel.ui;

import Hotel.entity.Hotel;
import Hotel.service.HotelService;
import Hotel.utils.ConsoleHelper;

import java.util.List;
import java.util.UUID;

public class DeleteHotelUI {
    private static final HotelService hotelService = ConsoleUI.hotelService; // reuse same instance from ConsoleUI

    public static void deleteHotelUi(){
        UUID ownerId = ConsoleUI.currentUser.getId();

        List<Hotel> hotels = ConsoleUI.hotelService.findAllHotels(ownerId);

        if (hotels.isEmpty()) {
            System.out.println("No hotels found.");
            return;
        }
        HotelsListUI.displayHotelsNames("Available Hotels");
        System.out.println("0. Cancel");

        int choice;
        do {
            choice = ConsoleHelper.readInt("Select the hotel number to delete (0 to cancel): ");
            if (choice == 0) {
                System.out.println("Delete cancelled.");
                return;
            }
            if (choice < 1 || choice > hotels.size()) {
                System.out.println("Invalid choice. Please select a number between 1 and " + hotels.size() + ", or 0 to cancel.");
            }
            else {
                Hotel selectedHotel = hotels.get(choice - 1);
                String hotelName = selectedHotel.getHotelName();
                hotelService.delete(ownerId, hotelName);
                return;
            }
        } while (true);
    }
}
