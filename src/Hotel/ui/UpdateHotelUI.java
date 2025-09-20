package Hotel.ui;

import Hotel.service.HotelService;
import Hotel.entity.Hotel;
import Hotel.utils.ConsoleHelper;

import java.util.List;
import java.util.UUID;

public class UpdateHotelUI {
    private static final HotelService hotelService = ConsoleUI.hotelService; // reuse same instance from ConsoleUI

    public static Object[] updateHotel() {
        UUID ownerId = ConsoleUI.currentUser.getId();
        // step 1: get list of hotels
        List<Hotel> hotels = ConsoleUI.hotelService.findAllHotels(ownerId);

        if (hotels.isEmpty()) {
            System.out.println("No hotels found.");
            return null;
        }

        // step 2: display hotels with order numbers
        HotelsListUI.displayHotelsNames("Available Hotels");
        System.out.println("0. Cancel");

        // step 3: get user choice with validation loop
        int choice;
        do {
            choice = ConsoleHelper.readInt("Select the hotel number to update (0 to cancel): ");
            if (choice == 0) {
                System.out.println("Update cancelled.");
                return null;
            }
            if (choice < 1 || choice > hotels.size()) {
                System.out.println("Invalid choice. Please select a number between 1 and " + hotels.size() + ", or 0 to cancel.");
            }
        } while (choice < 1 || choice > hotels.size());

        Hotel selectedHotel = hotels.get(choice - 1);

        // step 4: get new values from user
        String newName = ConsoleHelper.readLine("Enter new hotel name (current: " + selectedHotel.getHotelName() + "): ");
        String newAddress = ConsoleHelper.readLine("Enter new hotel address (current: " + selectedHotel.getAddress() + "): ");
        int newRooms = ConsoleHelper.readInt("Enter new total rooms number (current: " + selectedHotel.getAvailableRooms() + "): ");

        // step 5: return original name to find hotel, plus new values for update
        return new Object[]{selectedHotel.getHotelName(), newName, newAddress, newRooms};
    }
}
