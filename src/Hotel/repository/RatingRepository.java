package Hotel.repository;

public interface RatingRepository {
    // RATE a hotel
    void rate(String hotelName, double newRating);
}
