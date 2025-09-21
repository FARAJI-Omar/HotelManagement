package Hotel.service.serviceImp;

import Hotel.entity.Hotel;
import Hotel.entity.Reservation;
import Hotel.repository.HotelRepository;
import Hotel.repository.ReservationRepository;
import Hotel.service.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationServiceImpl implements ReservationService {
    private  final ReservationRepository reservationRepo;
    private  final HotelRepository hotelRepo;

    public ReservationServiceImpl(ReservationRepository reservationRepo, HotelRepository hotelRepo){
        this.reservationRepo = reservationRepo;
        this.hotelRepo = hotelRepo;
    }

    // RESERVE
    @Override
    public void reserve(String hotelName, String guestName, UUID clientId) {
        Hotel hotel = hotelRepo.findByName(hotelName).orElse(null);
        if (hotel == null){
            System.out.println("Hotel " + hotelName + " does not exist.");
            return;
        }
        if (hotel.getAvailableRooms() <= 0){
            System.out.println("No available rooms in " + hotelName);
            return;
        }
        List<Reservation> reservations = reservationRepo.reserve(hotelName, guestName, clientId);
        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        System.out.println("Reservation successful for " + guestName + " at " + hotelName);
    }

    // CANCEL RESERVATION
    @Override
    public void cancelReserve(String hotelName, String guestName, UUID clientId) {
        Hotel hotel = hotelRepo.findByName(hotelName).orElse(null);
        if (hotel == null) {
            System.out.println("Hotel " + hotelName + " does not exist.");
            return;
        }
        if (this.findClientReservations(clientId).isEmpty()) {
            System.out.println("You have no reservations to cancel!");
            return;
        }
        reservationRepo.cancelReserve(hotelName, guestName, clientId);
        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
        System.out.println("Reservation cancelled for " + guestName + " at " + hotelName);
    }

    // FIND current client reservations
    public List<Reservation> findClientReservations(UUID clientId){
        List<Reservation> clientReservations = reservationRepo.findByUserId(clientId);
        return clientReservations;
    }

    // GET history
    @Override
    public List<String> getClientHistory(UUID clientId) {
        return reservationRepo.getHistory(clientId);
    }
}


