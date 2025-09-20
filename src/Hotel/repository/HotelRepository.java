package Hotel.repository;

import Hotel.entity.Hotel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository {
    // CREATE
    void save(UUID hotelOwnerId, String hotelName, String hotelAddress, int totalRooms);

    // READ
    Optional<Hotel> findById(UUID hotelID);

    Optional<Hotel> findByName(String hotelName);

    List<Hotel> findAll(UUID ownerId);

    List<Hotel> findAll();

    // UPDATE
    void update(String originalHotelName, String newHotelName, String newHotelAddress, int newTotalRooms);
    void updateAvailability(String hotelName, int availableRooms);

    // DELETE
    void delete(String hotelName);

    // RATING
    void rate(String hotelName, double newRating);

}
