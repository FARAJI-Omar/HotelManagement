package Hotel.service.serviceImp;

import Hotel.entity.Hotel;
import Hotel.service.HotelService;
import Hotel.repository.HotelRepository;

import java.util.List;
import java.util.UUID;

import static Hotel.utils.Validator.hotelNotExists;

public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepo;

    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepo = hotelRepository;
    }

    //create hotel
    @Override
    public void createHotel(UUID hotelOwner, String hotelName, String hotelAddress, int totalRooms) {
        if (hotelNotExists(hotelOwner, hotelName, hotelRepo)) {
            hotelRepo.save(hotelOwner, hotelName, hotelAddress, totalRooms);
            System.out.println("Hotel created successfully.");
        } else {
            System.out.println("Hotel " + hotelName + " already exists.");
        }
    }
    //update hotel
    @Override
    public void update(UUID ownerId, String originalHotelName, String hotelName, String hotelAddress, int totalRooms) {
        if (hotelNotExists(ownerId, originalHotelName, hotelRepo)) {
            System.out.println("Hotel: " + originalHotelName + " does not exist.");
        } else {
            hotelRepo.update(originalHotelName, hotelName, hotelAddress, totalRooms);
            System.out.println("Hotel updated successfully!");
        }
    }
    //list all hotels
    @Override
    public List<Hotel> findAllHotels(UUID ownerId) {
        return hotelRepo.findAll(ownerId);
    }

    //list all hotels for clients
    @Override
    public List<Hotel> findAllHotels() {
        return hotelRepo.findAll();
    }
    //delete hotel
    @Override
    public void delete(UUID ownerId, String hotelName) {
        if (hotelNotExists(ownerId, hotelName, hotelRepo)) {
            System.out.println("Hotel: " + hotelName + " does not exist.");
        } else {
            hotelRepo.delete(hotelName);
            System.out.println("Hotel " + hotelName + " deleted successfully!");
        }
    }
}
