package Hotel.utils;

import Hotel.entity.Hotel;
import Hotel.repository.HotelRepository;
import java.util.List;
import java.util.UUID;

public class Validator {
    // name cant be empty
    public static boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

    // validate email format
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // password must be at least 6 characters
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    // hotel total rooms must be positive
    public static boolean isValidTotalRooms(int totalRooms) {
        return totalRooms > 0;
    }

    // don't allow hotel duplicate names
    public static boolean hotelNotExists(UUID ownerId, String hotelName, HotelRepository hotelRepository) {
        List<Hotel> hotels = hotelRepository.findAll(ownerId);
        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                return false;
            }
        }
        return true;
    }

}
