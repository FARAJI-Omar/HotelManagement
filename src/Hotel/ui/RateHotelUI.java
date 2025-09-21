package Hotel.ui;

import Hotel.entity.Hotel;
import Hotel.service.HotelService;
import Hotel.service.RatingService;
import Hotel.utils.ConsoleHelper;

import java.util.List;

public class RateHotelUI {
    private static final HotelService hotelService = ConsoleUI.hotelService;
    private static final RatingService ratingService = ConsoleUI.ratingService;

    public static void rateHotelUI() {
        // Get all hotels for selection
        List<Hotel> hotels = hotelService.findAllHotels();

        if (hotels.isEmpty()) {
            System.out.println("No hotels available to rate.");
            return;
        }

        System.out.println("\n===== Available Hotels ======\n");
        int counter = 1;
        for (Hotel hotel : hotels) {
            System.out.println(counter + ". Hotel Name: " + hotel.getHotelName());
            System.out.println("   Address: " + hotel.getAddress());
            System.out.println("   Rating: " + hotel.getRating());
            System.out.println("   Total Rates: " + hotel.getTotalRates());
            System.out.println("----------------------------------------\n");
            counter++;
        }

        System.out.println("0. Cancel");

        // Get user choice
        int choice = ConsoleHelper.readInt("Select the hotel number to rate (0 to cancel)");

        if (choice == 0) {
            System.out.println("Operation cancelled.");
            return;
        }

        if (choice < 1 || choice > hotels.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        // Get selected hotel
        Hotel selectedHotel = hotels.get(choice - 1);

        // Get rating input
        System.out.println("\nRating scale: 1.0 (Poor Service) - 5 (Excellent Service)\n");
        double rating = ConsoleHelper.readDouble("Enter your rating for " + selectedHotel.getHotelName() + " (1.0-5.0)");

        // Submit the rating
        ratingService.rate(selectedHotel.getHotelName(), rating);
    }
}
