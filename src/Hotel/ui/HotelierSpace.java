package Hotel.ui;

import Hotel.service.HotelService;
import Hotel.utils.ConsoleHelper;

import java.util.UUID;


public class HotelierSpace {
    private static final HotelService hotelService = ConsoleUI.hotelService; // reuse same instance from ConsoleUI

    public static void hotelierSpace() {
        UUID ownerId = ConsoleUI.currentUser.getId();

        while (true) {
            System.out.println("\n====== Hotelier Space ======\n");
            System.out.println("1. Add Hotel");
            System.out.println("2. Update Hotel");
            System.out.println("3. Delete Hotel");
            System.out.println("4. View Hotel list");
            System.out.println("5. Update Profile");
            System.out.println("6. Logout");

            int choice = ConsoleHelper.readInt("Choose an option");

            switch (choice) {
                case 1 -> {
                    try {
                        Object[] hotelInfos = CreateHotelUI.createHotelUi();
                        hotelService.createHotel(ownerId, hotelInfos[0].toString(), hotelInfos[1].toString(), (int) hotelInfos[2]);
                    } catch (Exception e) {
                        System.out.println("Failed to add hotel: " + e.getMessage());
                        System.out.println("Please try again.");
                    }
                }
                case 2 -> {
                    try {
                        Object[] updateInfos = UpdateHotelUI.updateHotel();
                        if (updateInfos != null) {
                            hotelService.update(
                                    ownerId,
                                updateInfos[0].toString(), // original hotel name
                                updateInfos[1].toString(), // new hotel name
                                updateInfos[2].toString(), // new hotel address
                                (int) updateInfos[3]       // new total rooms
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Failed to update hotel: " + e.getMessage());
                        System.out.println("Please try again.");
                    }
                }
                case 3 -> {
                    DeleteHotelUI.deleteHotelUi();
                }
                case 4 -> {
                    HotelsListUI.displayHotels("List of your managed Hotels");
                }
                case 5 -> {
                    System.out.println("Update Profile!");
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    ConsoleUI.isHotelier = false;
                    ConsoleUI.currentUser = null;
                    ConsoleUI.start();
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}
