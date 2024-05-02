package edu.mu.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.mu.hotel.rooms.RoomType;
import edu.mu.hotel.rooms.RoomTypeManager;

public class Reservation {
    private static int reservationCounter = 1;
    private final int reservationId;
    private final int customerId;
    private final String roomType;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private boolean isActive; 

    // Static list to hold all reservations
    

    public Reservation(int customerId, String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationCounter++;
        this.customerId = customerId;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isActive = true;
        
    }

    //create a new reservation
    
    

    //Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getRoomType() {
        return roomType;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

