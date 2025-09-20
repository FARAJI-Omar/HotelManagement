package Hotel.service;

import Hotel.entity.Hotel;

import java.util.List;
import java.util.UUID;

public interface HotelService {
    void createHotel(UUID ownerId, String hotelName, String hotelAddress, int totalRooms);

//    // READ
//    Optional<Hotel> findById(UUID hotelID);
//
//    Optional<Hotel> findByName(String hotelName);

    List<Hotel> findAllHotels(UUID ownerId);

//    // UPDATE
    void update(UUID ownerId, String originalHotelName, String newHotelName, String newHotelAddress, int newTotalRooms);
//
//    void updateAvailability(String hotelName, int availableRooms);
//
//    // DELETE
//    void delete(String hotelName);
//
//    // RATING
//    void rate(String hotelName, double newRating);
}
