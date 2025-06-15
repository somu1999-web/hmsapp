package com.hmsapp_test.repository;

import com.hmsapp_test.entity.RoomsAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomsAvailabilityRepository extends JpaRepository<RoomsAvailability, Long> {

    @Query("select r from RoomsAvailability r where r.roomType = :roomType and " +
            "r.date between :fromDate and :toDate")
    List<RoomsAvailability> findAvailableRooms(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("roomType") String roomType
    );

}