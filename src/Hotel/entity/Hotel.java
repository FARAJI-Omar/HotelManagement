package Hotel.entity;

import java.util.UUID;

public class Hotel {
    private UUID hotelID;
    private String hotelName;
    private String address;
    private int availableRooms;
    private double rating;
    private int totalRates; // count of rates number    

    public Hotel(String email, String hotelName, String password, String address, int availableRooms, double rating, int totalRates){
        this.hotelID = UUID.randomUUID();
        this.hotelName = hotelName;
        this.address = address != null ? address : "not set yet";
        this.availableRooms = availableRooms > 0 ? availableRooms : 0;
        this.rating = rating >= 0 ? rating : 0;
        this.totalRates = totalRates >= 0 ? totalRates : 0;
    }

    // getters
    public UUID getHotelId() { return hotelID; }
    public String getHotelName() { return hotelName; }
    public String getAddress() { return address; }
    public int getAvailableRooms() { return availableRooms; }
    public double getRating() { return rating; }
    public int getTotalRates() { return totalRates; }

    // setters
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    public void setAddress(String address) { this.address = address; }
    public void setAvailableRooms(int availableRooms) { this.availableRooms = availableRooms; }
    public void setRating(double rating) { this.rating = rating; }
    public void setTotalRates(int totalRates) { this.totalRates = totalRates; }

}
