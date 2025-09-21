package Hotel.repository.repoImp;

import Hotel.entity.Hotel;
import Hotel.repository.HotelRepository;
import Hotel.repository.RatingRepository;

import java.util.Optional;

public class InMemoryRatingRepository implements RatingRepository {
    private final HotelRepository hotelRepository;

    public InMemoryRatingRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // RATING
    @Override
    public void rate(String hotelName, double newRating) {
        Optional<Hotel> existingHotel = hotelRepository.findByName(hotelName);
        if (existingHotel.isEmpty()) {
            throw new IllegalArgumentException("Hotel with name '" + hotelName + "' not found");
        } else {
            Hotel hotel = existingHotel.get();
            // Calculate new hotel rating
            double currentRating = hotel.getRating();
            int totalRates = hotel.getTotalRates();

            double updatedRating = (currentRating * totalRates + newRating) / (totalRates + 1);
            hotel.setRating(updatedRating);
            hotel.setTotalRates(totalRates + 1);
            hotelRepository.updateRating(hotel);
        }
    }
}
