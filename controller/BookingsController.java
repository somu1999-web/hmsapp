package com.hmsapp_test.controller;

import com.hmsapp_test.entity.Booking;
import com.hmsapp_test.entity.Property;
import com.hmsapp_test.entity.RoomsAvailability;
import com.hmsapp_test.repository.BookingRepository;
import com.hmsapp_test.repository.PropertyRepository;
import com.hmsapp_test.repository.RoomsAvailabilityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsController {

    private RoomsAvailabilityRepository roomsAvailabilityRepository;
    private PropertyRepository propertyRepository;
    private  BookingRepository bookingRepository;


    public BookingsController(RoomsAvailabilityRepository roomsAvailabilityRepository, PropertyRepository propertyRepository,
                              BookingRepository bookingRepository) {
        this.roomsAvailabilityRepository = roomsAvailabilityRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/search/rooms")
    public ResponseEntity<?> searchRoomsAndBook(
            @RequestParam LocalDate fromDate ,
            @RequestParam LocalDate toDate ,
            @RequestParam String roomType ,
            @RequestParam long propertyId ,
            @RequestBody Booking bookings
            ){
        List<RoomsAvailability> rooms = roomsAvailabilityRepository.findAvailableRooms(fromDate, toDate, roomType);
        Property property = propertyRepository.findById(propertyId).get();

        for (RoomsAvailability r : rooms){
            if (r.getTotalRooms() == 0){
                return new ResponseEntity<>("No rooms Available" , HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        bookings.setProperty(property);
        Booking savedBookings = bookingRepository.save(bookings);
        return new ResponseEntity<>(savedBookings, HttpStatus.CREATED);
    }
}
