package Hotel.ui;

import Hotel.utils.ConsoleHelper;
import Hotel.utils.Validator;

public class CreateHotelUI {
    public static Object[] createHotelUi() {
        System.out.println("\nPlease enter hotel infos:");
        String hotelName;
        do {
            hotelName = ConsoleHelper.readLine("Hotel Name");
            if (!Validator.isValidName(hotelName)) {
                System.out.println("Hotel name cannot be empty. Try again.");
            }
        } while (!Validator.isValidName(hotelName));

        String hotelAddress;
        do {
            hotelAddress = ConsoleHelper.readLine("Hotel Address");
            if (!Validator.isValidName(hotelAddress)) {
                System.out.println("Hotel Address cannot be empty. Try again.");
            }
        } while (!Validator.isValidName(hotelAddress));

        int totalRooms;
        do {
            totalRooms = ConsoleHelper.readInt("Available Rooms");
            if (!Validator.isValidTotalRooms(totalRooms)) {
                System.out.println("Total rooms must be a valid positive number. Try again.");
            }
        } while (!Validator.isValidTotalRooms(totalRooms));

        return new Object[] { hotelName, hotelAddress, totalRooms };
    }
}
