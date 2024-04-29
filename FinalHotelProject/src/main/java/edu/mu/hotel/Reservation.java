import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private static int reservationCounter = 1;
    private final int reservationId;
    private final int customerId;
    private final String roomType;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private boolean isActive; 

    // Static list to hold all reservations
    private static final List<Reservation> reservations = new ArrayList<>();

    public Reservation(int customerId, String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationCounter++;
        this.customerId = customerId;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isActive = true;
        reservations.add(this); //add to the list of reservations
    }

    //create a new reservation
    
    public static Reservation createReservation(int customerId, String roomType, LocalDate checkIn, LocalDate checkOut) {
        if (checkAvailability(roomType, checkIn, checkOut)) {
            // Assuming there is a method to reduce the room availability count
            RoomTypeManager.getInstance().decreaseRoomAvailability(roomType);
            return new Reservation(customerId, roomType, checkIn, checkOut);
        }
        return null; //return null if no availability
    }

    //static method to check room availability
    public static boolean checkAvailability(String roomType, LocalDate checkIn, LocalDate checkOut) {
        // Assuming there is a RoomTypeManager class to handle room types and their counts
        RoomTypeManager roomTypeManager = RoomTypeManager.getInstance();
        int totalRoomsOfType = roomTypeManager.getTotalRooms(roomType);

        //count how many rooms are booked for the given room type during the requested date range
        long bookedRooms = reservations.stream()
            .filter(reservation -> reservation.isActive() &&
                                  reservation.getRoomType().equals(roomType) &&
                                  reservation.getCheckOutDate().isAfter(checkIn) &&
                                  reservation.getCheckInDate().isBefore(checkOut))
            .count();

        //check if there are any rooms left
        return bookedRooms < totalRoomsOfType;
    }


    public static boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId && reservation.isActive()) {
                reservation.setActive(false);
                RoomType roomType = RoomTypeManager.getRoomTypeByName(reservation.getRoomType());
                if (roomType != null) {
                    roomType.setRoomsAvailable(roomType.getRoomsAvailable() + 1);
                }
                return true;
            }
        }
        return false;
    }

    //method to confirm reservation details
    public String confirmBooking() {
        if (!this.isActive) {
            return "Reservation ID " + reservationId + " is cancelled.";
        }
        //display or return reservation details
        return "Reservation confirmed: ID " + reservationId +
                "\nCustomer ID: " + customerId +
                "\nRoom Type: " + roomType +
                "\nCheck-In Date: " + checkInDate +
                "\nCheck-Out Date: " + checkOutDate;
    }

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

    //get all active reservations
    public static List<Reservation> getActiveReservations() {
        List<Reservation> activeReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.isActive()) {
                activeReservations.add(reservation);
            }
        }
        return activeReservations;
    }

    //get specific reservation by ID
    public static Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null; //if no reservation is found with the given ID
    }
}

