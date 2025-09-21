package Hotel.service.serviceImp;

import Hotel.repository.RatingRepository;
import Hotel.service.RatingService;
import Hotel.utils.Validator;

public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void rate(String hotelName, double newRating) {
        // Validate rating using Validator class
        if (!Validator.isValidRating(newRating)) {
            System.out.println("Rating must be between 1.0 and 5.0");
            return;
        }

        try {
            ratingRepository.rate(hotelName, newRating);
            System.out.println("Rating submitted successfully for " + hotelName);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
