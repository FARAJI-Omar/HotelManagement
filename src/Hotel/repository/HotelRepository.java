package Hotel.repository;

import Hotel.entity.Hotel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository {
    // CREATE
    void save(String hotelName, String hotelAddress, int totalRooms);

    // READ
    Optional<Hotel> findById(UUID hotelID);

    Optional<Hotel> findByName(String hotelEmail);

    List<Hotel> findAvailableHotels();

    List<Hotel> findAll();

    // UPDATE
    void update(Hotel hotel);
    void updateAvailability(Hotel hotel, int availableRooms);

    // DELETE
    void delete(Hotel hotel);

    // RATING
    void rate(Hotel hotel, double newRating);

}
