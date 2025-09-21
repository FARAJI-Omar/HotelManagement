package Hotel.repository.repoImp;

import Hotel.entity.Hotel;
import Hotel.repository.HotelRepository;

import java.util.*;

public class InMemoryHotelRepository implements HotelRepository {
    private final Map<UUID, Hotel> hotels = new HashMap<>();

    // CREATE
    @Override
    public void save(UUID hotelOwner, String hotelName, String address, int totalRooms) {
        Hotel hotel = new Hotel(hotelOwner, hotelName, address, totalRooms, 0.0, 0);
        hotels.put(hotel.getHotelId(), hotel);
    }

    // READ
    @Override
    public Optional<Hotel> findById(UUID hotelID) {
        return Optional.ofNullable(hotels.get(hotelID));
    }

    @Override
    public Optional<Hotel> findByName(String hotelName) {
        for (Hotel hotel : hotels.values()){
            if (hotel.getHotelName().equalsIgnoreCase(hotelName)){
                return Optional.of(hotel);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Hotel> findAll(UUID ownerId) {
        List<Hotel> ownerHotels = new ArrayList<>();
        for (Hotel hotel : hotels.values()) {
            if (hotel.getOwnerId() != null && hotel.getOwnerId().equals(ownerId)) {
                ownerHotels.add(hotel);
            }
        }
        return ownerHotels;
    }

    @Override
    public List<Hotel> findAll() {
        return new ArrayList<>(hotels.values());
    }

    // UPDATE
    @Override
    public void update(String originalHotelName, String newHotelName, String newHotelAddress, int newTotalRooms) {
        Optional<Hotel> existingHotel = findByName(originalHotelName);
        if (existingHotel.isEmpty()) {
            throw new IllegalArgumentException("Hotel with name '" + originalHotelName + "' not found");
        } else {
            Hotel hotel = existingHotel.get();

            // Update hotel properties
            hotel.setHotelName(newHotelName);
            hotel.setAddress(newHotelAddress);
            hotel.setAvailableRooms(newTotalRooms);

            hotels.put(hotel.getHotelId(), hotel);
        }
    }

    @Override
    public void updateAvailability(String hotelName, int availableRooms) {
        Optional<Hotel> existingHotel = findByName(hotelName);
        if (existingHotel.isEmpty()) {
            throw new IllegalArgumentException("Hotel with name '" + hotelName + "' not found");
        } else {
            Hotel hotel = existingHotel.get();
            // Update the hotel available rooms
            hotel.setAvailableRooms(availableRooms);

            hotels.put(hotel.getHotelId(), hotel);
        }
    }

    // DELETE
    public void delete(String hotelName) {
        Optional<Hotel> existingHotel = findByName(hotelName);
        if (existingHotel.isEmpty()) {
            throw new IllegalArgumentException("Hotel with name '" + hotelName + "' not found");
        } else {
            Hotel hotel = existingHotel.get();
            hotels.remove(hotel.getHotelId());
        }
    }

    // RATING
    @Override
    public void updateRating(Hotel hotel) {
        hotels.put(hotel.getHotelId(), hotel);
    }
}
