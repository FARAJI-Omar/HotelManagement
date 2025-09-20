package Hotel.ui;

import Hotel.repository.HotelRepository;
import Hotel.repository.repoImp.InMemoryHotelRepository;
import Hotel.service.HotelService;
import Hotel.service.serviceImp.HotelServiceImpl;
import Hotel.entity.Hotel;

import java.util.List;
import java.util.UUID;


public class HotelsListUI {
    private static final HotelService hotelService = ConsoleUI.hotelService; // reuse same instance from ConsoleUI

    // Display all hotels with details
    public static void displayHotels(String message) {
        System.out.println("\n===== " + message + " ======\n");

        UUID ownerId = ConsoleUI.currentUser.getId();

        List<Hotel> hotels = hotelService.findAllHotels(ownerId);
        if (hotels.isEmpty()) {
            System.out.println("No hotels found.");
        } else {
            hotels.forEach(hotel -> {
                System.out.println("Hotel Name: " + hotel.getHotelName());
                System.out.println("Address: " + hotel.getAddress());
                System.out.println("Available Rooms: " + hotel.getAvailableRooms());
                System.out.println("Rating: " + hotel.getRating());
                System.out.println("Total Rates: " + hotel.getTotalRates());
                System.out.println("----------------------------------------\n");
            });
        }
    }

    // Display hotels with just names
    public static void displayHotelsNames(String message) {
        System.out.println("\n===== " + message + " ======\n");
        UUID ownerId = ConsoleUI.currentUser.getId();
        List<Hotel> hotelsNames = hotelService.findAllHotels(ownerId);
        if (hotelsNames.isEmpty()){
            System.out.println("No hotels found.");
        } else {
            int counter = 1;
            for (Hotel hotel : hotelsNames) {
                System.out.println(counter + ". Hotel Name: " + hotel.getHotelName() + "\n");
                counter++;
             }
        }
    }
}
